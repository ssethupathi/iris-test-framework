package com.temenos.interaction.test.context;

public interface ServiceConfig {

	public static final String HTTP_HEADER_CONTENT_TYPE = "HttpHeader_Content-Type";
	public static final String HTTP_HEADER_ACCEPT = "HttpHeader_Accept";
	
	String getValue(String propertyName);

}
