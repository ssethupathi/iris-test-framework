package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public class DefaultPayloadWrapper implements PayloadWrapper {

	private PayloadTransformer transformer;
	private Map<String, Entity> entities;

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
			entities = new HashMap<String, Entity>();
//			List<EntityTransformer<Entry>> entryHandlers = transformer
//					.entityWrappers();
//			for (EntityTransformer<Entry> entryHandler : entryHandlers) {
//				Entity entity = new DefaultEntryWrapper<Entry>(
//						"verCustomer_Inputs", entryHandler);
//				entities.put(entity.id(), entity);
//			}
		}
	}

	@Override
	public Entity entity(String id) {
		return entities.get(id);
	}

	@Override
	public void setTransformer(PayloadTransformer transformer) {
		this.transformer = transformer;
	}
}
