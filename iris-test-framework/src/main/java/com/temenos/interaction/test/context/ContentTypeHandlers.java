package com.temenos.interaction.test.context;

import java.util.HashMap;
import java.util.Map;

import com.temenos.interaction.test.PayloadHandler;
import com.temenos.interaction.test.internal.PayloadHandlerFactory;

public class ContentTypeHandlers {

	private Map<String, PayloadHandlerFactory<? extends PayloadHandler>> payloadHandlersFactory;

	public ContentTypeHandlers() {
		this.payloadHandlersFactory = new HashMap<String, PayloadHandlerFactory<? extends PayloadHandler>>();
	}

	public void registerForPayload(String contentType,
			final Class<? extends PayloadHandler> transformerClass) {
		payloadHandlersFactory.put(contentType,
				PayloadHandlerFactory.createFactory(transformerClass));
	}

	public PayloadHandlerFactory<? extends PayloadHandler> getPayloadHandlerFactory(
			String contentType) {
		return payloadHandlersFactory.get(contentType);
	}
}
