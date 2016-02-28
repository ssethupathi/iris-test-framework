package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public class DefaultPayloadWrapper implements PayloadWrapper {

	private PayloadHandler transformer;
	private Map<String, EntityWrapper> entities;

	public DefaultPayloadWrapper() {
	}

	@Override
	public List<Link> links() {
		return transformer.getLinks();
	}

	@Override
	public Entity entity() {
		checkAndBuildEntities();
		return entities.isEmpty() ? null : entities().get(0);
	}

	@Override
	public List<Entity> entities() {
		checkAndBuildEntities();
		return Collections.unmodifiableList(new ArrayList<Entity>(entities
				.values()));
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
	public Entity entity(String id) {
		return entities.get(id);
	}

	@Override
	public void setHandler(PayloadHandler transformer) {
		this.transformer = transformer;
	}
}
