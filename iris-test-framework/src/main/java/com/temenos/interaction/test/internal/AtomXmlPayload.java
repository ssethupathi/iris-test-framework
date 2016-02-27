package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Payload;

public class AtomXmlPayload implements Payload {

	private AtomFeedHandler transformer;
	private Map<String, Entity> entities;

	public AtomXmlPayload(String feed) {
		this.transformer = new AtomFeedHandler(feed);
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
			List<EntityHandler<Entry>> entryHandlers = transformer
					.entityHandlers();
			for (EntityHandler<Entry> entryHandler : entryHandlers) {
				Entity entity = new EntityWrapper<Entry>("verCustomer_Inputs",
						entryHandler);
				entities.put(entity.id(), entity);
			}
		}
	}

	@Override
	public Entity entity(String id) {
		return entities.get(id);
	}
}
