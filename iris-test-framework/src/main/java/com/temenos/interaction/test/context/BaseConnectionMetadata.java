package com.temenos.interaction.test.context;

import java.util.Properties;

public class BaseConnectionMetadata implements ConnectionMetadata {

	private Properties connectionProperties = new Properties();

	@Override
	public String getValue(String propertyName) {
		return connectionProperties.getProperty(propertyName, "");
	}

}
