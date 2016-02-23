package com.temenos.interaction.test.context;

import java.util.Properties;

public class BaseConnectionConfig implements ConnectionConfig {

	private Properties connectionProperties = new Properties();
	
	public BaseConnectionConfig(Properties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	@Override
	public String getValue(String name) {
		return connectionProperties.getProperty(name, "");
	}	
}
