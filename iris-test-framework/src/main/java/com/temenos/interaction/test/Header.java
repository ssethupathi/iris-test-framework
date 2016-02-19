package com.temenos.interaction.test;

import java.util.Collection;

public interface Header {
	void set(String name, String value);

	String get(String name);
	
	Collection<String> names();
}
