package com.temenos.interaction.test.context;


public class SystemConnectionMetadata implements ConnectionMetadata {

	private ConnectionMetadata connectionMetadata;

	public SystemConnectionMetadata(ConnectionMetadata connectionMetadata) {
		this.connectionMetadata = connectionMetadata;
	}

	@Override
	public String getValue(String propertyName) {
		return System.getProperty(propertyName,
				connectionMetadata.getValue(propertyName));
	}
}
