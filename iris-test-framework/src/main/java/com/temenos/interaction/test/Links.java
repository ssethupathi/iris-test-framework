package com.temenos.interaction.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.temenos.interaction.test.internal.CallableLink;
import com.temenos.interaction.test.internal.LinkWrapper;
import com.temenos.interaction.test.internal.SessionCallback;

public class Links {

	private List<Link> links = new ArrayList<Link>();
	private boolean linksNotMapped = true;
	private Map<String, Link> linksByRel = new HashMap<String, Link>();
	private Map<String, Link> linksByHref = new HashMap<String, Link>();
	private Map<String, Link> linksByTitle = new HashMap<String, Link>();
	private SessionCallback sessionCallback;

	private Links(List<Link> links, SessionCallback sessionCallback) {
		this.sessionCallback = sessionCallback;
		this.links = links; // TODO deep copy
	}

	private Links() {
	}

	public CallableLink byRel(String rel) {
		if (linksNotMapped) {
			mapLinks();
		}
		return new LinkWrapper(linksByRel.get(rel), sessionCallback);
	}

	public Link byHref(String rel) {
		if (linksNotMapped) {
			mapLinks();
		}
		return linksByHref.get(rel);
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

	public static Links create(List<Link> links, SessionCallback sessionCallback) {
		return new Links(links, sessionCallback);
	}

	public static Links createEmpty() {
		return new Links();
	}

	private void mapLinks() {
		for (Link link : links) {
			linksByRel.put(link.rel(), link);
		}
		linksNotMapped = true;
	}
}
