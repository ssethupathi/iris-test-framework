package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.http.HttpHeader;

public class RequestDataImpl implements RequestData {

	private Entity entity;
	private HttpHeader header;

	public RequestDataImpl(HttpHeader header, Entity entity) {
		this.header = header;
		this.entity = entity;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public Entity entity() {
		return entity;
	}
}
