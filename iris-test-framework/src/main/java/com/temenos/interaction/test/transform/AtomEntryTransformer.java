package com.temenos.interaction.test.transform;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.internal.AbstractAtomTransformer;

public class AtomEntryTransformer extends AbstractAtomTransformer implements
		EntityTransformer<Entry> {

	private String entityName;
	private Entry entry;

	public AtomEntryTransformer(String entityName, Entry entry) {
		this.entityName = entityName;
		this.entry = entry;
	}

	public List<Link> getLinks() {
		return null;
	}

	public Entry getEntityHolder() {
		return entry;
	}

	public String getValue(String fqPropertyName) {
		String[] pathParts = fqPropertyName.split("/");
		Element content = entry.getFirstChild(new QName(NS_ATOM, "content"));
		Element parent = content.getFirstChild(new QName(NS_ODATA_METADATA,
				"properties"));
		int pathIndex = 0;
		while (pathIndex < (pathParts.length - 1)) {
			String pathPart = pathParts[pathIndex];
			if (!pathPart.matches(REGX_VALID_ELEMENT)) {
				throw new IllegalArgumentException("Invalid part '" + pathPart
						+ "' in fully qualified property name '"
						+ fqPropertyName + "'");
			}
			parent = getChild(parent, buildElementName(pathPart),
					extractIndex(pathPart));
			pathIndex++;
		}
		if (parent != null) {
			Element property = parent.getFirstChild(new QName(NS_ODATA,
					pathParts[pathParts.length - 1]));
			if (property != null) {
				return property.getText();
			}
		}
		// TODO WARN
		return "";
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
		return path;
	}

	private Element getChild(Element parent, String childName, int expectedIndex) {
		Element currentChildElement = parent.getFirstChild(new QName(NS_ODATA,
				"element"));
		if (expectedIndex == 0) {
			return currentChildElement.getFirstChild(new QName(NS_ODATA,
					childName));
		}
		int index = 1;
		while (currentChildElement
				.getNextSibling(new QName(NS_ODATA, "element")) != null) {
			currentChildElement = currentChildElement.getNextSibling(new QName(
					NS_ODATA, "element"));
			if (expectedIndex == index++) {
				return currentChildElement.getFirstChild(new QName(NS_ODATA,
						childName));
			}
		}
		// TODO WARN
		return null;
	}
}
