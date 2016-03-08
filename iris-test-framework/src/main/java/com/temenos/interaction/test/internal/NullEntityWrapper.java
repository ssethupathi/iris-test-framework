package com.temenos.interaction.test.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Links;

public class NullEntityWrapper implements EntityWrapper {

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
		return null; // TODO?
	}

	@Override
	public void setHandler(EntityHandler handler) {
		// TODO?
	}

	@Override
	public void setSessionCallback(SessionCallback sessionCallback) {
		// TODO
	}

	@Override
	public void setValue(String fqPropertyName, String value) {
		// TODO?
	}

	@Override
	public InputStream getContent() {
		try {
			return IOUtils.toInputStream("", "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
