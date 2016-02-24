package com.temenos.interaction.test.internal;

import java.util.HashMap;
import java.util.Map;

public class MediaTypeHandlers {

	private Map<String, MediaTypeHandler> handlers;

	public MediaTypeHandlers() {
		this.handlers = new HashMap<String, MediaTypeHandler>();
	}

	public void register(String mediaType, MediaTypeHandler handler) {
		handlers.put(mediaType, handler);
	}

	public MediaTypeHandler get(String mediaType) {
		return handlers.get(mediaType);
	}

}
