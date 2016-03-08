package com.temenos.interaction.test;

import java.util.List;

public interface Entity {

	String id();

	String get(String fqName);

	int count(String fqName);

	List<Link> links();

	Links link();

}