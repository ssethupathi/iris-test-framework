package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public class LinkImpl implements Link {

	private String path;
	private boolean isInline;
	private Entity entity;

	@Override
	public String path() {
		return path;
	}

	@Override
	public boolean isInline() {
		return isInline;
	}

	@Override
	public Entity entity() {
		return entity;
	}

	private LinkImpl(String href) {
		this.path = href;
		this.isInline = false;
		this.entity = null;
	}

	private LinkImpl(String href, Entity entity) {
		this.path = href;
		this.isInline = true;
		this.entity = entity;
	}

	public static Link newLink(String href) {
		return new LinkImpl(href);
	}

	public static Link newLink(String href, Entity entity) {
		if (entity == null) {
			return newLink(href);
		}
		return new LinkImpl(href, entity);
	}

	@Override
	public String rel() {
		// TODO Auto-generated method stub
		return null;
	}
}
