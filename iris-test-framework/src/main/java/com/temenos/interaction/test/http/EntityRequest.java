package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Entity;

public class EntityRequest implements HttpRequest {

	private HttpHeader header;
	private Entity entity;

	public EntityRequest(HttpHeader header, Entity entity) {
		this.entity = entity;
	}

	public HttpHeader header() {
		return header;
	}

	@Override
	public String payload() {
		return entity.content();
	}
}
