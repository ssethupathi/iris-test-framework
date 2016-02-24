package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;

public class RequestDataImpl implements RequestData {

	private Entity entity;
	private Header header;

	public RequestDataImpl(Header header, Entity entity
			) {
		this.header = header;
		this.entity = entity;
	}

	@Override
	public Header header() {
		return header;
	}

	@Override
	public Entity entity() {
		return entity;
	}
}
