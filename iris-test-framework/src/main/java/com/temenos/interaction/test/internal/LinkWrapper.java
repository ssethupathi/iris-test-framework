package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Link;

public class LinkWrapper implements CallableLink {

	private Link link;
	private SessionCallback sessionCallback;

	public LinkWrapper(Link link, SessionCallback sessionCallback) {
		this.link = link;
		this.sessionCallback = sessionCallback;
	}

	@Override
	public String title() {
		return link.title();
	}

	@Override
	public String href() {
		return link.href();
	}

	@Override
	public String rel() {
		return link.rel();
	}

	@Override
	public String id() {
		return link.id();
	}

	@Override
	public boolean hasEmbeddedPayload() {
		return link.hasEmbeddedPayload();
	}

	@Override
	public Payload embeddedPayload() {
		return link.embeddedPayload();
	}

	@Override
	public String urlStr() {
		return link.urlStr();
	}

	@Override
	public Url url() {
		return new UrlWrapper(urlStr(), sessionCallback);
	}
}
