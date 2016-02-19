package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;
import com.temenos.interaction.test.http.HttpHeader;

public class InputDataSession implements InputSession {

	private Entity entity;
	private Header header = new HttpHeader();
	private String queryParam = "";

	@Override
	public void header(String name, String value) {
		header.set(name, value);
	}

	@Override
	public void queryParam(String value) {
		this.queryParam = value;
	}

	@Override
	public Entity entity(String name) {
		return entity;
	}

	@Override
	public void setProperty(String name, String value) {
		//
	}

	@Override
	public void entity(Entity entity) {
		this.entity = entity;
	}

	@Override
	public Header header() {
		return header;
	}

	@Override
	public String queryParam() {
		return queryParam;
	}
}
