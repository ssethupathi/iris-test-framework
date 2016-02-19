package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;

public interface InputSession {

	Header header();
	
	Entity entity(String name);
	
	String queryParam();

	void header(String name, String value);

	void queryParam(String value);

	void entity(Entity entity);

	void setProperty(String name, String value);

}
