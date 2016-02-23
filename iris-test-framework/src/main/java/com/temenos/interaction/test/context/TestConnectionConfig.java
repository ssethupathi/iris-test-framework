package com.temenos.interaction.test.context;

import java.util.Properties;

public class TestConnectionConfig implements ConnectionConfig {

	private Properties testConnProps = new Properties();
	private ConnectionConfig sysConnConfig;

	TestConnectionConfig(ConnectionConfig sysConnConfig) {
		this.sysConnConfig = sysConnConfig;
	}

	@Override
	public String getValue(String propertyName) {
		return testConnProps.getProperty(propertyName,
				sysConnConfig.getValue(propertyName));
	}

	public void setValue(String propertyName, String value) {
		testConnProps.setProperty(propertyName, value);
	}
}
