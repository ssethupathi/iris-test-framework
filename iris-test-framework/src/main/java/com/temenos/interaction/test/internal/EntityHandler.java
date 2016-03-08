package com.temenos.interaction.test.internal;

import java.io.InputStream;
import java.util.List;

import com.temenos.interaction.test.Link;

public interface EntityHandler {

	String getId();
	
	List<Link> getLinks();

	String getValue(String fqPropertyName);
	
	void setValue(String fqPropertyName, String value);
	
	int getCount(String fqPropertyName);

	void setContent(InputStream stream);
	
	InputStream getContent();
	
}
