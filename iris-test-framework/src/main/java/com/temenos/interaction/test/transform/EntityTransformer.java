package com.temenos.interaction.test.transform;

import java.util.List;

import com.temenos.interaction.test.Link;

public interface EntityTransformer<T> {

	List<Link> getLinks();

	String getValue(String fqPropertyName);

	T getEntityHolder();

}
