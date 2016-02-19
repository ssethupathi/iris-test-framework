package com.temenos.interaction.test.http;

public class HrefBuilder {
	private String rel;
	private String entityName;
	private String id;

	private HrefBuilder(String rel, String entityName, String id) {
		this.rel = rel;
		this.entityName = entityName;
		this.id = id;
	}

	private HrefBuilder(String entityName, String id) {
		this.entityName = entityName;
		this.id = id;
	}

	public String buildHref() {
		if (entityName == null) {
			throw new IllegalStateException("Entity name is not set yet");
		}
		if ("new".equals(rel)) {
			return entityName + "/" + rel;
		} else if ("input".equals(rel)) {
			return entityName + "('" + id + "')/" + rel;
		} else {
			return entityName + "s()";
		}
	}
}