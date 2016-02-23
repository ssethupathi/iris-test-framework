package com.temenos.interaction.test.context;


public class SystemConnectionConfig implements ConnectionConfig {

	private ConnectionConfig connectionMetadata;

	public SystemConnectionConfig(ConnectionConfig connectionProperties) {
		this.connectionMetadata = connectionProperties;
	}

	@Override
	public String getValue(String propertyName) {
		return System.getProperty(propertyName,
				connectionMetadata.getValue(propertyName));
	}
}
