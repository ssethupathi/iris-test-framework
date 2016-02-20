package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;
import com.temenos.interaction.test.http.HttpHeader;

public class RequestSessionImpl implements RequestSession {

	private Entity entity;
	private Header header = new HttpHeader();
	private String queryParam = "";
	
	private String entityName;
	protected String id;

	@Override
	public void header(String name, String value) {
		header.set(name, value);
	}

	@Override
	public void queryParam(String value) {
		this.queryParam = value;
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
	public void entityId(String id) {
		this.id = id;
	}

	@Override
	public void entityName(String name) {
		this.entityName = name;
	}
}
