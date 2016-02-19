package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Property;

public class SimpleProperty implements Property<String> {
	
	public String name;
	public String value;
	
	public SimpleProperty(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String name() {
		return name;
	}
	
	public String value() {
		return value;
	}
}
