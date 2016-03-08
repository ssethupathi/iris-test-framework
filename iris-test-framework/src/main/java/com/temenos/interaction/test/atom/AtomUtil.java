package com.temenos.interaction.test.atom;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.abdera.model.Element;

public class AtomUtil {

	public static final String MEDIA_TYPE = "application/atom+xml";
	public final static String NS_ATOM = "http://www.w3.org/2005/Atom";
	public final static String NS_ODATA = "http://schemas.microsoft.com/ado/2007/08/dataservices";
	public final static String NS_ODATA_METADATA = "http://schemas.microsoft.com/ado/2007/08/dataservices/metadata";
	public final static String NS_ODATA_RELATED = "http://schemas.microsoft.com/ado/2007/08/dataservices/related";

	public final static String REGX_VALID_ELEMENT = "[a-zA-Z0-9_]+(\\(\\d+\\))*";
	public final static String REGX_ELEMENT_WITH_INDEX = "[a-zA-Z0-9_]+(\\(\\d+\\))+";

	public static String extractRel(String relAttributeValue) {
		int spaceIndex = relAttributeValue.indexOf(" ");
		if (spaceIndex > 0) {
			return relAttributeValue.substring(spaceIndex).trim();
		} else {
			return relAttributeValue;
		}
	}
	
	public static String getBaseUrl(Element element) {
		try {
			return element.getBaseUri().toURL().toString();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
