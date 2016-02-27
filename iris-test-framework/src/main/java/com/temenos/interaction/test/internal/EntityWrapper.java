package com.temenos.interaction.test.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public class EntityWrapper<T> implements Entity {

	private String name;
	private Map<String, Link> namedLinks;
	private EntityHandler<T> entityHandler;

	public EntityWrapper(String name, EntityHandler<T> entityHandler) {
		this.name = name;
		this.entityHandler = entityHandler;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String id() {
		return entityHandler.getId();
	}

	@Override
	public String get(String fqName) {
		return entityHandler.getValue(fqName);
	}

	@Override
	public int count(String fqName) {
		return entityHandler.getCount(fqName);
	}

	@Override
	public Collection<Link> links() {
		checkAndBuildLinks();
		return Collections.unmodifiableCollection(namedLinks.values());
	}

	@Override
	public Link link(String name) {
		checkAndBuildLinks();
		return namedLinks.get(name);
	}

	private void checkAndBuildLinks() {
		if (namedLinks != null) {
			return;
		}
		List<Link> links = entityHandler.getLinks();
		namedLinks = new HashMap<String, Link>();
		for (Link link : links) {
			namedLinks.put(link.path(), link);
		}
	}

	@Override
	public String content() {
		return null;
	}
}
