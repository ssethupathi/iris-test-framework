package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.http.HttpHeader;

public class RequestDataImpl implements RequestData {

	private EntityWrapper entity;
	private HttpHeader header;

	public RequestDataImpl(HttpHeader header, EntityWrapper entity) {
		this.header = header;
		this.entity = entity;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public EntityWrapper entity() {
		return entity;
	}
}
