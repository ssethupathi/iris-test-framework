package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Href;
import com.temenos.interaction.test.Link;

public class LinkImpl implements Link {

	private Href href;
	private boolean isInline;
	private Entity entity;

	@Override
	public Href href() {
		return href;
	}

	@Override
	public boolean isInline() {
		return isInline;
	}

	@Override
	public Entity entity() {
		return entity;
	}

	private LinkImpl(Href href) {
		this.href = href;
		this.isInline = false;
		this.entity = null;
	}

	private LinkImpl(Href href, Entity entity) {
		this.href = href;
		this.isInline = true;
		this.entity = entity;
	}

	public static Link newLink(Href href) {
		return new LinkImpl(href);
	}

	public static Link newLink(Href href, Entity entity) {
		if (entity == null) {
			return newLink(href);
		}
		return new LinkImpl(href, entity);
	}
}
