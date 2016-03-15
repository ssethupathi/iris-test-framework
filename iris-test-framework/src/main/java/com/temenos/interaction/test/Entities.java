package com.temenos.interaction.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.interaction.test.internal.EntityWrapper;

public class Entities {

	private List<EntityWrapper> entities = new ArrayList<EntityWrapper>();
	private boolean entitiesNotMapped = false;
	private Map<String, EntityWrapper> entitiesById = new HashMap<String, EntityWrapper>();

	public Entities(List<EntityWrapper> entities) {
		this.entities = entities; // TODO deep copy
	}

	public EntityWrapper byId(String id) {
		if (entitiesNotMapped) {
			mapEntities();
		}
		return entitiesById.get(id);
	}

	public List<? extends Entity> all() {
		return entities;
	}

	private void mapEntities() {
		for (EntityWrapper entity : entities) {
			entitiesById.put(entity.id(), entity);
		}
	}
}
