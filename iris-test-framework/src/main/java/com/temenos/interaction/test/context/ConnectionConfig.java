package com.temenos.interaction.test.context;

public interface ConnectionConfig {

	public static final String ENDPOINT_URI = "URI";
	public static final String USER_NAME = "USERNAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String SERVICE_ROOT = "COMPANY";

	public String getValue(String propertyName);
}
