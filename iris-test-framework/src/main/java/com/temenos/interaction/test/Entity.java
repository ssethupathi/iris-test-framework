package com.temenos.interaction.test;


public interface Entity {

	String id();

	String get(String fqName);

	int count(String fqName);

	Links links();

}