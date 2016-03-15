package com.temenos.interaction.test.atom;

import static com.temenos.interaction.test.atom.AtomUtil.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.parser.ParseException;
import org.apache.commons.io.IOUtils;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.internal.EntityHandler;
import com.temenos.interaction.test.internal.LinkImpl;

public class AtomEntryHandler implements EntityHandler {

	private Entry entry;

	public AtomEntryHandler() {
		entry = new Abdera().newEntry();
	}

	public List<Link> getLinks() {
		return convertLinks(entry.getLinks());
	}

	public String getId() {
		String fullPath = entry.getId().getPath();
		// TODO strong logic
		if (fullPath.contains("('") && fullPath.endsWith("')")) {
			return fullPath.substring(fullPath.indexOf("'") + 1,
					fullPath.lastIndexOf("'"));
		}
		return "";
	}

	public int getCount(String fqPropertyName) {
		String[] pathParts = validateAndParsePath(fqPropertyName);
		Element parent = getParent(pathParts);
		String propertyName = pathParts[pathParts.length - 1];
		int count = 0;
		if (parent != null) {
			Element child = parent.getFirstChild(new QName(NS_ODATA,
					propertyName));
			if (child != null) {
				do {
					count++;
				} while (child
						.getNextSibling(new QName(NS_ODATA, propertyName)) != null);
			}
		}
		return count;
	}

	public String getValue(String fqPropertyName) {
		Element property = getProperty(fqPropertyName);
		if (property != null) {
			return property.getText();
		} else {
			return "";
		}
	}

	@Override
	public void setValue(String fqPropertyName, String value) {
		Element property = getProperty(fqPropertyName);
		if (property != null) {
			property.setText(value);
		} else {
			// setNewValue(fqPropertyName, value);
			throw new RuntimeException("New value addition not supported");
		}
	}

	private Element getProperty(String fqPropertyName) {
		String[] pathParts = validateAndParsePath(fqPropertyName);
		Element parent = getParent(pathParts);
		String propertyName = pathParts[pathParts.length - 1];
		if (parent != null) {
			return parent.getFirstChild(new QName(NS_ODATA, propertyName));
		} else {
			return null;
		}
	}

	private Element getParent(String... pathParts) {
		Element content = entry.getFirstChild(new QName(NS_ATOM, "content"));
		Element parent = content.getFirstChild(new QName(NS_ODATA_METADATA,
				"properties"));
		int pathIndex = 0;
		while (pathIndex < (pathParts.length - 1)) {
			String pathPart = pathParts[pathIndex];
			parent = getSpecificChild(parent, buildElementName(pathPart), 0);
			parent = getSpecificChild(parent, "element", extractIndex(pathPart));
			pathIndex++;
		}
		return parent;
	}

	private int extractIndex(String path) {
		if (path.matches(REGX_ELEMENT_WITH_INDEX)) {
			String indexStr = path.substring(path.indexOf("(") + 1,
					path.indexOf(")"));
			return Integer.parseInt(indexStr);
		}
		return 0;
	}

	private String buildElementName(String path) {
		if (path.matches(REGX_ELEMENT_WITH_INDEX)) {
			return path.substring(0, path.indexOf("("));
		}
		return path;
	}

	private String[] validateAndParsePath(String fqName) {
		String[] pathParts = fqName.split("/");
		for (String pathPart : pathParts) {
			if (!pathPart.matches(REGX_VALID_ELEMENT)) {
				throw new IllegalArgumentException("Invalid part '" + pathPart
						+ "' in fully qualified property name");
			}
		}
		return pathParts;
	}

	private Element getSpecificChild(Element parent, String childName,
			int expectedIndex) {
		Element child = parent.getFirstChild(new QName(NS_ODATA, childName));
		if (expectedIndex == 0) {
			return child;
		} else {
			int index = 1;
			while (child != null) {
				child = child.getNextSibling(new QName(NS_ODATA, childName));
				if (expectedIndex == index++) {
					return child;
				}
			}
		}
		// TODO WARN
		return null;
	}

	private List<Link> convertLinks(
			List<org.apache.abdera.model.Link> abderaLinks) {
		List<Link> links = new ArrayList<Link>();
		for (org.apache.abdera.model.Link abderaLink : abderaLinks) {
			AtomLinkHandler linkHandler = new AtomLinkHandler(abderaLink);
			links.add(new LinkImpl.Builder(abderaLink.getAttributeValue("href")).baseUrl(linkHandler.getBaseUri())
					.rel(linkHandler.getRel()).id(getId())
					.payload(linkHandler.getEmbeddedPayload()).build());
		}
		return links;
	}

	@Override
	public void setContent(InputStream stream) {
		if (stream == null) {
			throw new IllegalArgumentException("Entity input stream is null");
		}
		Document<Element> entityDoc = null;
		try {
			entityDoc = new Abdera().getParser().parse(stream);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Unexpected entity for media type '" + AtomUtil.MEDIA_TYPE
							+ "'.", e);
		}
		QName rootElementQName = entityDoc.getRoot().getQName();
		if (new QName(AtomUtil.NS_ATOM, "entry").equals(rootElementQName)) {
			entry = (Entry) entityDoc.getRoot();
		} else {
			throw new IllegalArgumentException(
					"Unexpected entity for media type '" + MEDIA_TYPE
							+ "'. Payload [" + entityDoc.getRoot().toString()
							+ "]");
		}
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	@Override
	public InputStream getContent() {
		return IOUtils.toInputStream(getContent(entry));
	}

	private String getContent(Element element) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			element.writeTo(baos);
			return baos.toString("UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}