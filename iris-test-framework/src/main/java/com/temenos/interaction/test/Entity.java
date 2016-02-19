package com.temenos.interaction.test;

import java.util.List;

import com.temenos.interaction.test.internal.Resettable;

public interface Entity extends Resettable {

	String name();

	String id();

	String get(String name);

//	GroupProperty root();

	List<Link> links();

	void id(String id);
}
