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
	private boolean linksNotYetMapped = true;
	private Map<String, Link> linksByRel = new HashMap<String, Link>();
	private Map<String, Link> linksByHref = new HashMap<String, Link>();
	private Map<String, Link> linksByTitle = new HashMap<String, Link>();
	private Map<String, Link> linksById = new HashMap<String, Link>();
	private SessionCallback sessionCallback;

	private Links(List<Link> links, SessionCallback sessionCallback) {
		this.sessionCallback = sessionCallback;
		this.links = links; // TODO deep copy
	}

	private Links() {
	}

	public CallableLink byRel(String rel) {
		if (linksNotYetMapped) {
			mapLinks();
		}
		return new LinkWrapper(linksByRel.get(rel), sessionCallback);
	}

	public CallableLink byHref(String href) {
		if (linksNotYetMapped) {
			mapLinks();
		}
		return new LinkWrapper(linksByHref.get(href), sessionCallback);
	}

	public CallableLink byTitle(String regex) {
		if (linksNotYetMapped) {
			mapLinks();
		}
		for (String title : linksByTitle.keySet()) {
			if (Pattern.compile(regex).matcher(title).matches()) {
				return new LinkWrapper(linksByTitle.get(title), sessionCallback);
			}
		}
		return null;
	}

	public CallableLink byId(String id) {
		if (linksNotYetMapped) {
			mapLinks();
		}
		return new LinkWrapper(linksById.get(id), sessionCallback);
	}

	public List<Link> all() {
		return links; // TODO defensive copy
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
			if (!link.id().isEmpty()) {
				linksById.put(link.id(), link);
			}
		}
		linksNotYetMapped = false;
	}

}
