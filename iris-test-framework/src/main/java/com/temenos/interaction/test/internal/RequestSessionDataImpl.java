package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;

public class RequestSessionDataImpl implements RequestSessionData {

	private Entity entity;
	private Header header;
	private String queryParam = "";

	public RequestSessionDataImpl(Header header, Entity entity,
			String queryParam) {
		this.header = header;
		this.entity = entity;
		this.queryParam = queryParam;
	}

	@Override
	public Header header() {
		return header;
	}

	@Override
	public String queryParam() {
		return queryParam;
	}

	@Override
	public Entity entity() {
		return entity;
	}
}
