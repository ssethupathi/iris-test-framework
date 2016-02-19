package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.List;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public class EntityImpl implements Entity {

	private String name = "";
	private String id = "";
	private List<Link> links = new ArrayList<Link>();
	private CollectionProperty properties;

	@Override
	public String name() {
		return name;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public String get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Link> links() {
		return links;
	}

	@Override
	public void id(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	private EntityImpl(Builder builder) {
		this.name = builder.name;
		this.id = builder.id;
		this.links = builder.links;
	}

	public static class Builder {
		private String name;
		private String id;
		private List<Link> links;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder setProperty(String name, String value) {
			return this;
		}

		public Entity build() {
			return new EntityImpl(this);
		}
	}
}
