package com.temenos.interaction.test.internal;

import java.util.HashMap;
import java.util.Map;

public class EntityHandlersRegistry {

	private Map<String, EntityHandler> handlers;

	public EntityHandlersRegistry() {
		this.handlers = new HashMap<String, EntityHandler>();
	}

	public void register(String mediaType, EntityHandler handler) {
		handlers.put(mediaType, handler);
	}

	public EntityHandler get(String mediaType) {
		return handlers.get(mediaType);
	}
}
