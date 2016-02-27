package com.temenos.interaction.test.internal;

import java.util.List;

import com.temenos.interaction.test.Link;

public interface EntityHandler<T> {

	String getId();
	
	List<Link> getLinks();

	String getValue(String fqPropertyName);
	
	int getCount(String fqPropertyName);

	T getEntityHolder();

}
