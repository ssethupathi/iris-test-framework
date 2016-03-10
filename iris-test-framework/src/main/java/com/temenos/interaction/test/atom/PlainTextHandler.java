package com.temenos.interaction.test.atom;

import java.util.Collections;
import java.util.List;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.PayloadHandler;
import com.temenos.interaction.test.internal.EntityWrapper;
import com.temenos.interaction.test.internal.NullEntityWrapper;

public class PlainTextHandler implements PayloadHandler {

	private String plainText = null;
	private String parameter;

	@Override
	public boolean isCollection() {
		return false;
	}

	@Override
	public List<Link> links() {
		return Collections.emptyList();
	}

	@Override
	public List<EntityWrapper> entities() {
		return Collections.emptyList();
	}

	@Override
	public EntityWrapper entity() {
		return new NullEntityWrapper();
	}

	@Override
	public void setPayload(String payload) {
		plainText = payload;
	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}
