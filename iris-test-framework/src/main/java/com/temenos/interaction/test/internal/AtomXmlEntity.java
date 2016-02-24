package com.temenos.interaction.test.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.transform.AtomEntryTransformer;

public class AtomXmlEntity implements Entity {

	private String name;
	private Map<String, Link> namedLinks;
	private AtomEntryTransformer transformer;

	public AtomXmlEntity(String name, Entry entry) {
		this.name = name;
		this.transformer = new AtomEntryTransformer(name, entry);
	}

	@Override
	public String name() {
		return name;
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
		List<Link> links = transformer.getLinks();
		namedLinks = new HashMap<String, Link>();
		for (Link link : links) {
			namedLinks.put(link.path(), link);
		}
	}
}
