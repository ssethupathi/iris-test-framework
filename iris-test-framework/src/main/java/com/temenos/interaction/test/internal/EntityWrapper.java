package com.temenos.interaction.test.internal;

import java.io.InputStream;

import com.temenos.interaction.test.Entity;

public interface EntityWrapper extends Entity {

	void setHandler(EntityHandler handler);
	
	void setSessionCallback(SessionCallback sessionCallback);
	
	void setValue(String fqPropertyName, String value);
	
	InputStream getContent();
}
