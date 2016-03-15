package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Link;

public class LinkImpl implements Link {

	private String baseUrl;
	private String rel;
	private String href;
	private String title;
	private String id;
	private boolean hasEmbeddedPayload;
	private Payload embeddedPayload;

	private LinkImpl(Builder builder) {
		this.baseUrl = builder.baseUrl;
		this.rel = builder.rel;
		this.href = builder.href;
		this.title = builder.title;
		this.id = builder.id;
		this.embeddedPayload = builder.embeddedPayload;
		this.hasEmbeddedPayload = this.embeddedPayload == null ? false : true;
	}

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
	public String id() {
		return id;
	}

	@Override
	public String toString() {
		return "LinkImpl [rel=" + rel + ", href=" + href + ", title=" + title
				+ ", baseUrl=" + baseUrl + ", hasEmbeddedPayload="
				+ hasEmbeddedPayload + "]";
	}

	public static class Builder {
		private String id = "";
		private String rel = "";
		private String title = "";
		private String href = "";
		private String baseUrl = "";
		private Payload embeddedPayload;

		public Builder(String href) {
			this.href = href;
		}

		public Builder rel(String rel) {
			this.rel = rel;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder baseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder payload(Payload payload) {
			this.embeddedPayload = payload;
			return this;
		}

		public Link build() {
			return new LinkImpl(this);
		}
	}
}
