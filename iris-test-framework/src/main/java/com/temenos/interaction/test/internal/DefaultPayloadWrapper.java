package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.PayloadHandler;

public class DefaultPayloadWrapper implements PayloadWrapper {

	private PayloadHandler transformer;
	private Map<String, EntityWrapper> entities;

	public DefaultPayloadWrapper() {
	}

	@Override
	public List<Link> links() {
		return transformer.links();
	}

	@Override
	public EntityWrapper entity() {
		return transformer.entity();
	}

	@Override
	public List<EntityWrapper> entities() {
		checkAndBuildEntities();
		return Collections.unmodifiableList(new ArrayList<EntityWrapper>(
				entities.values()));
	}

	private void checkAndBuildEntities() {
		if (entities == null) {
			entities = new HashMap<String, EntityWrapper>();
			for (EntityWrapper entityWrapper : transformer.entities()) {
				entities.put(entityWrapper.id(), entityWrapper);
			}
		}
	}

	@Override
	public EntityWrapper entity(String id) {
		return entities.get(id);
	}

	@Override
	public void setHandler(PayloadHandler transformer) {
		this.transformer = transformer;
	}

	@Override
	public boolean isCollection() {
		return transformer.isCollection();
	}
}
