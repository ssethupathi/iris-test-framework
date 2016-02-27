package com.temenos.interaction.test.internal;

import static com.temenos.interaction.test.internal.AtomTransformerHelper.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public class AtomEntryHandler implements EntityHandler<Entry> {

	private String entityName;
	private String entryContent;
	private Entry entry;

	public AtomEntryHandler(String entityName, String entryContent) {
		this.entityName = entityName;
		this.entryContent = entryContent;
	}

	public List<Link> getLinks() {
		return convertLinks(getEntityHolder().getLinks());
	}

	public Entry getEntityHolder() {
		if (entry == null) {
			// build entry
		}
		return entry;
	}

	public String getId() {
		String fullPath = getEntityHolder().getId().getPath();
		// TODO strong logic
		if (fullPath.contains("('") && fullPath.endsWith("')")) {
			return fullPath.substring(fullPath.indexOf("'") + 1,
					fullPath.lastIndexOf("'"));
		}
		throw new IllegalStateException("Invalid path " + fullPath);
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
		String[] pathParts = validateAndParsePath(fqPropertyName);
		Element parent = getParent(pathParts);
		String propertyName = pathParts[pathParts.length - 1];
		if (parent != null) {
			Element property = parent.getFirstChild(new QName(NS_ODATA,
					propertyName));
			if (property != null) {
				return property.getText();
			}
		}
		// TODO WARN
		return "";
	}

	private Element getParent(String... pathParts) {
		Element content = getEntityHolder().getFirstChild(
				new QName(NS_ATOM, "content"));
		Element parent = content.getFirstChild(new QName(NS_ODATA_METADATA,
				"properties"));
		int pathIndex = 0;
		while (pathIndex < (pathParts.length - 1)) {
			String pathPart = pathParts[pathIndex];
			parent = getSpecificChild(parent, buildElementName(pathPart),
					extractIndex(pathPart));
			parent = getSpecificChild(parent, "element", 0);
			pathIndex++;
		}
		return parent;
	}

	private int extractIndex(String path) {
		if (path.matches(REGX_ELEMENT_WITH_INDEX)) {
			String indexStr = path.substring(path.indexOf("(") + 1,
					path.indexOf(""));
			return Integer.parseInt(indexStr);
		}
		return 0;
	}

	private String buildElementName(String path) {
		if (path.matches(REGX_ELEMENT_WITH_INDEX)) {
			return entityName + "_" + path.substring(0, path.indexOf("("));
		}
		return entityName + "_" + path;
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
			while (child.getNextSibling(new QName(NS_ODATA, childName)) != null) {
				child = child.getNextSibling(new QName(NS_ODATA, childName));
				if (expectedIndex == index++) {
					return child.getFirstChild(new QName(NS_ODATA, childName));
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
			String href = abderaLink.getAttributeValue("href");
			Entity inlineEntity = buildInlineEntity(abderaLink);
			links.add(LinkImpl.newLink(href, inlineEntity));
		}
		return links;
	}

	private Entity buildInlineEntity(org.apache.abdera.model.Link abderaLink) {
		Element entryElement = abderaLink.getFirstChild(new QName(NS_ODATA,
				"entry"));
		if (entryElement != null) {
			Document<Entry> entryDoc = entryElement.getDocument();
			String hrefPath = abderaLink.getHref().getPath();
			Class type = Entry.class;
			return new EntityWrapper<Entry>(hrefPath, EntityHandlerFactory.createFactory(Entry.class, "").newTransformer());
		}
		return null;
	}
}