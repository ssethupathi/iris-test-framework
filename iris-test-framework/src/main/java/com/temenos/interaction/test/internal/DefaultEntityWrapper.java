package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.interaction.test.Link;

public class DefaultEntityWrapper implements EntityWrapper {

	private Map<String, Link> namedLinks;
	private EntityHandler transformer;

	public DefaultEntityWrapper() {
	}

	@Override
	public String id() {
		return transformer.getId();
	}

	@Override
	public String get(String fqName) {
		return transformer.getValue(fqName);
	}

	@Override
	public int count(String fqName) {
		return transformer.getCount(fqName);
	}

	@Override
	public List<Link> links() {
		checkAndBuildLinks();
		return new ArrayList<Link>(namedLinks.values());
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
		List<Link> links = transformer.getLinks();
		namedLinks = new HashMap<String, Link>();
		for (Link link : links) {
			namedLinks.put(link.rel(), link);
		}
	}

	@Override
	public String content() {
		return null;
	}

	@Override
	public void setHandler(EntityHandler transformer) {
		this.transformer = transformer;
	}
}
