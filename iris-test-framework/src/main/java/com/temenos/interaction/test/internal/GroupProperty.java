package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Property;

public class GroupProperty implements Property<CollectionProperty> {

	private String name;
	private CollectionProperty value;

	public GroupProperty(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public CollectionProperty value() {
		return value;
	}

	public void set(CollectionProperty prop) {
		this.value = prop;
	}
}
