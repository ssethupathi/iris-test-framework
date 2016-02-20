package com.temenos.interaction.test.internal;

public abstract class AbstractAtomTransformer {

	protected final static String NS_ATOM = "http://www.w3.org/2005/Atom";
	protected final static String NS_ODATA = "http://schemas.microsoft.com/ado/2007/08/dataservices";
	protected final static String NS_ODATA_METADATA = "http://schemas.microsoft.com/ado/2007/08/dataservices/metadata";
	protected final static String NS_ODATA_RELATED = "http://schemas.microsoft.com/ado/2007/08/dataservices/related";

	protected final static String REGX_VALID_ELEMENT = "[a-zA-Z0-9]+(\\(\\d+\\))*";
	protected final static String REGX_ELEMENT_WITH_INDEX = "[a-zA-Z0-9]+(\\(\\d+\\))+";
}
