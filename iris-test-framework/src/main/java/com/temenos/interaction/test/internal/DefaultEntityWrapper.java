package com.temenos.interaction.test.internal;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Links;

public class DefaultEntityWrapper implements EntityWrapper {

	private Map<String, Link> namedLinks;
	private EntityHandler transformer;
	private SessionCallback sessionCallback;

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
	public Links link() {
		if (sessionCallback == null) {
			return Links.createEmpty();
		} else {
			return Links.create(links(), sessionCallback);
		}
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
	public InputStream getContent() {
		return transformer.getContent();
	}

	@Override
	public void setValue(String fqPropertyName, String value) {
		transformer.setValue(fqPropertyName, value);
	}

	@Override
	public void setHandler(EntityHandler transformer) {
		this.transformer = transformer;
	}

	@Override
	public void setSessionCallback(SessionCallback sessionCallback) {
		this.sessionCallback = sessionCallback;
	}
}
