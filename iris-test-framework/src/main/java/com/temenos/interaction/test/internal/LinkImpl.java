package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Payload;

public class LinkImpl implements Link {

	private String path;
	private boolean hasEmbeddedPayload;
	private Payload embeddedPayload;

	@Override
	public String path() {
		return path;
	}

	@Override
	public boolean hasEmbeddedPayload() {
		return hasEmbeddedPayload;
	}

	@Override
	public Payload embeddedPayload() {
		return embeddedPayload;
	}

	private LinkImpl(String href) {
		this.path = href;
		this.hasEmbeddedPayload = false;
		this.embeddedPayload = null;
	}

	private LinkImpl(String href, Payload embeddedPayload) {
		this.path = href;
		this.hasEmbeddedPayload = true;
		this.embeddedPayload = embeddedPayload;
	}

	public static Link newLink(String href) {
		return new LinkImpl(href);
	}

	public static Link newLink(String href, Payload embeddedPayload) {
		return new LinkImpl(href, embeddedPayload);
	}

	@Override
	public String rel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Url url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String id() {
		// TODO Auto-generated method stub
		return null;
	}
}
