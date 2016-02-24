package com.temenos.interaction.test;

import java.util.Collection;

public interface Entity {

	String name();
	
	String id();

	String get(String fqName);
	
	int count(String fqName);

	Collection<Link> links();
	
	Link link(String name);
	
	String content();

}