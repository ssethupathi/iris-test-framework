package com.temenos.interaction.test.internal;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Links;

public class NullEntity implements EntityWrapper {

	@Override
	public String id() {
		return "";
	}

	@Override
	public String get(String fqName) {
		return "";
	}

	@Override
	public int count(String fqName) {
		return 0;
	}

	@Override
	public List<Link> links() {
		return Collections.emptyList();
	}

	@Override
	public Links link() {
		return Links.createEmpty();
	}

	@Override
	public void setHandler(EntityHandler handler) {
	}

	@Override
	public void setSessionCallback(SessionCallback sessionCallback) {
	}

	@Override
	public void setValue(String fqPropertyName, String value) {
		
	}

	@Override
	public InputStream getContent() {
		return null;
	}
}
