package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.Collection;

import com.temenos.interaction.test.Property;

public class CollectionProperty implements Property<Collection<Property>> {

	private Collection<Property> value;

	public CollectionProperty() {
		value = new ArrayList<Property>();
	}

	@Override
	public String name() {
		throw new UnsupportedOperationException(
				"No 'name' set for CollectionProperty");
	}

	@Override
	public Collection<Property> value() {
		return value;
	}

	public void add(String name, String value) {
		this.value.add(new SimpleProperty(name, value));
	}
	
	public void add(String name, Property value) {
		this.value.add(value);
	}
}
