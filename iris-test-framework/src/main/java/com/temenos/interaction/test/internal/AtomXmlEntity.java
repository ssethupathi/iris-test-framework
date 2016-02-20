package com.temenos.interaction.test.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public class AtomXmlEntity implements Entity {

	private String name;
	private String id;
	private Map<String, Link> links;
	private Entry entry;

	public AtomXmlEntity(String name, Entry entry) {
		this.name = name;
		this.entry = entry;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String get(String fqName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Link> links() {
		if (links == null) {
			// do something
		}
		return Collections.unmodifiableCollection(links.values());
	}

	@Override
	public Link link(String name) {
		if (links == null) {
			// do something
		}
		return links.get(name);
	}
}
