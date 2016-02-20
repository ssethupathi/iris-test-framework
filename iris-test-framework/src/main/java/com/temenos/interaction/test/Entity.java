package com.temenos.interaction.test;

import java.util.Collection;

import com.temenos.interaction.test.internal.Resettable;

public interface Entity extends Resettable {

	String name();
	
	String id();

	String get(String fqName);

	Collection<Link> links();
	
	Link link(String name);

}