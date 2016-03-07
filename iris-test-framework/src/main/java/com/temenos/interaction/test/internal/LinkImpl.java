package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Link;

public class LinkImpl implements Link {

	private String rel;
	private String href;
	private String title;
	private boolean hasEmbeddedPayload;
	private Payload embeddedPayload;

	@Override
	public String href() {
		return href;
	}

	@Override
	public boolean hasEmbeddedPayload() {
		return hasEmbeddedPayload;
	}

	@Override
	public Payload embeddedPayload() {
		return embeddedPayload;
	}

	private LinkImpl(String rel, String href) {
		this.rel = rel;
		this.href = href;
		this.hasEmbeddedPayload = false;
		this.embeddedPayload = null;
	}

	private LinkImpl(String rel, String href, Payload embeddedPayload) {
		this.rel = rel;
		this.href = href;
		this.embeddedPayload = embeddedPayload;
		if (embeddedPayload != null) {
			this.hasEmbeddedPayload = true;
		}
	}

	public static Link newLink(String rel, String href) {
		return new LinkImpl(rel, href);
	}

	public static Link newLink(String rel, String href, Payload embeddedPayload) {
		return new LinkImpl(rel, href, embeddedPayload);
	}

	@Override
	public String rel() {
		return rel;
	}

	@Override
	public Url url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public String toString() {
		return "LinkImpl [rel=" + rel + ", href=" + href + ", title=" + title
				+ ", hasEmbeddedPayload=" + hasEmbeddedPayload + "]";
	}
}
