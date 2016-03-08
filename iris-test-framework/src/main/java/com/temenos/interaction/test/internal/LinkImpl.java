package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Link;

public class LinkImpl implements Link {

	private String baseUrl;
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

	private LinkImpl(String baseUrl, String rel, String href) {
		this.baseUrl = baseUrl;
		this.rel = rel;
		this.href = href;
		this.hasEmbeddedPayload = false;
		this.embeddedPayload = null;
	}

	private LinkImpl(String baseUrl, String rel, String href,
			Payload embeddedPayload) {
		this.baseUrl = baseUrl;
		this.rel = rel;
		this.href = href;
		this.embeddedPayload = embeddedPayload;
		if (embeddedPayload != null) {
			this.hasEmbeddedPayload = true;
		}
	}

	public static Link newLink(String baseUrl, String rel, String href) {
		return new LinkImpl(baseUrl, rel, href);
	}

	public static Link newLink(String baseUrl, String rel, String href,
			Payload embeddedPayload) {
		return new LinkImpl(baseUrl, rel, href, embeddedPayload);
	}

	@Override
	public String rel() {
		return rel;
	}

	@Override
	public String urlStr() {
		return baseUrl + href;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public String toString() {
		return "LinkImpl [rel=" + rel + ", href=" + href + ", title=" + title
				+ ", baseUrl=" + baseUrl + ", hasEmbeddedPayload="
				+ hasEmbeddedPayload + "]";
	}
}
