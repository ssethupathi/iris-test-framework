package com.temenos.interaction.test.context;

public class SystemServiceConfig implements ServiceConfig {

	private ServiceConfig serviceMetadata;

	public SystemServiceConfig(ServiceConfig serviceMetadata) {
		this.serviceMetadata = serviceMetadata;
	}

	@Override
	public String getValue(String propertyName) {
		return System.getProperty(propertyName,
				serviceMetadata.getValue(propertyName));
	}
}
