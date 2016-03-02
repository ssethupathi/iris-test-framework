package com.temenos.interaction.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Links {

	private List<Link> links;
	private boolean linksNotMapped = true;
	private Map<String, Link> linksByRel = new HashMap<String, Link>();
	private Map<String, Link> linksById = new HashMap<String, Link>();
	private Map<String, Link> linksByTitle = new HashMap<String, Link>();

	private Links(List<Link> links) {
		// TODO deep copy
		this.links = links;
	}

	public Link byRel(String rel) {
		if (linksNotMapped) {
			mapLinks();
		}
		return linksByRel.get(rel);
	}

	public Link byId(String id) {
		if (linksNotMapped) {
			mapLinks();
		}
		return linksById.get(id);
	}

	public Link byTitle(String regex) {
		if (linksNotMapped) {
			mapLinks();
		}
		for (String key : linksByTitle.keySet()) {
			if (Pattern.compile(regex).matcher(key).matches()) {
				return linksByTitle.get(key);
			}
		}
		return null;
	}

	public static Links create(List<Link> links) {
		return new Links(links);
	}

	private void mapLinks() {
		for (Link link : links) {
			linksByRel.put(link.rel(), link);
		}
	}
}
