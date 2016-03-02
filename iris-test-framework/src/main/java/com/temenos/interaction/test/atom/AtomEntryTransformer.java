package com.temenos.interaction.test.atom;

import static com.temenos.interaction.test.atom.AtomTransformerHelper.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.context.ContextFactory;
import com.temenos.interaction.test.internal.DefaultEntityWrapper;
import com.temenos.interaction.test.internal.DefaultPayloadWrapper;
import com.temenos.interaction.test.internal.EntityHandler;
import com.temenos.interaction.test.internal.EntityHandlerFactory;
import com.temenos.interaction.test.internal.EntityWrapper;
import com.temenos.interaction.test.internal.LinkImpl;
import com.temenos.interaction.test.internal.PayloadWrapper;

public class AtomEntryTransformer implements EntityHandler {

	private Entry entry;

	public AtomEntryTransformer() {
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
		Element content = entry.getFirstChild(new QName(NS_ATOM, "content"));
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
			Payload embeddedPayload = buildEmbeddedPayload(abderaLink);
			links.add(LinkImpl.newLink(href, embeddedPayload));
		}
		return links;
	}

	private Payload buildEmbeddedPayload(org.apache.abdera.model.Link abderaLink) {
		List<EntityWrapper> entityWrappers = new ArrayList<EntityWrapper>();
		Element feedElement = abderaLink.getFirstChild(new QName(NS_ODATA,
				"feed"));
		if (feedElement != null) {
			Document<Feed> feedDoc = feedElement.getDocument();
			Feed feed = feedDoc.getRoot();
			entityWrappers = buildEmbeddedEntities(feed.getEntries());
		} else {
			Element entryElement = abderaLink.getFirstChild(new QName(NS_ODATA,
					"entry"));
			if (entryElement != null) {
				Document<Entry> entryDoc = entryElement.getDocument();
				List<Entry> entries = new ArrayList<Entry>();
				entries.add(entryDoc.getRoot());
				entityWrappers = buildEmbeddedEntities(entries);
			}
		}
		PayloadWrapper payloadWrapper = new DefaultPayloadWrapper();
		return payloadWrapper;
	}

	private List<EntityWrapper> buildEmbeddedEntities(List<Entry> entries) {
		String mediaType = "application/atom+xml";
		EntityHandlerFactory factory = ContextFactory.getContext()
				.entityHandlersRegistry().getEntityHandlerFactory(mediaType);
		List<EntityWrapper> entityWrappers = new ArrayList<EntityWrapper>();
		for (Entry entry : entries) {
			try {
				EntityWrapper wrapper = new DefaultEntityWrapper();
				wrapper.setHandler(factory.handler(entry.getContentStream()));
				entityWrappers.add(wrapper);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return entityWrappers;
	}

	@Override
	public void setContent(InputStream stream) {
		entry.setContent(stream);
	}

	@Override
	public void setContent(Object entity) {
		if (entity instanceof Entry) {
			this.entry = (Entry) entity;
		} else {
			throw new RuntimeException("Invalid");
		}

	}
}