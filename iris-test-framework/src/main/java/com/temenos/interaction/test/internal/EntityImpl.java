package com.temenos.interaction.test.internal;

import java.util.Collections;
import java.util.List;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public class EntityImpl implements Entity {

	private String name = "";
	private String id = "";

	private EntityImpl(String name, String id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String get(String name) {
		return null;
	}

	@Override
	public List<Link> links() {
		return Collections.<Link> emptyList();
	}

	@Override
	public void reset() {
		// do nothing
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public Link link(String name) {
		return null;
	}

	public static Entity newBlankEntity(String name, String id) {
		return new EntityImpl(name, id);
	}
}
