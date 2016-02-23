package com.temenos.interaction.test.context;

import java.util.Properties;

public class BaseServiceConfig implements ServiceConfig {

	private Properties baseServProps = new Properties();
	
	public BaseServiceConfig(Properties baseServProps) {
		this.baseServProps = baseServProps;
	}

	@Override
	public String getValue(String propertyName) {
		return baseServProps.getProperty(propertyName, "");
	}

}
