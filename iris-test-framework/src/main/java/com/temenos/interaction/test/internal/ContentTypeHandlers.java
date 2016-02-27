package com.temenos.interaction.test.internal;

import java.util.HashMap;
import java.util.Map;

public class ContentTypeHandlers {

	private Map<String, EntityWrapperFactory<? extends EntityTransformer>> entityHandlersFactory;
	private Map<String, PayloadWrapperFactory<? extends PayloadTransformer>> payloadHandlersFactory;

	public ContentTypeHandlers() {
		this.entityHandlersFactory = new HashMap<String, EntityWrapperFactory<? extends EntityTransformer>>();
		this.payloadHandlersFactory = new HashMap<String, PayloadWrapperFactory<? extends PayloadTransformer>>();
	}

	public void registerForEntity(String contentType,
			final Class<? extends EntityTransformer> transformerClass) {
		entityHandlersFactory.put(contentType,
				EntityWrapperFactory.createFactory(transformerClass));
	}

	public EntityWrapperFactory<? extends EntityTransformer> getEntityHandlerFactory(
			String contentType) {
		return entityHandlersFactory.get(contentType);
	}

	public void registerForPayload(String contentType,
			final Class<? extends PayloadTransformer> transformerClass) {
		payloadHandlersFactory.put(contentType,
				PayloadWrapperFactory.createFactory(transformerClass));
	}

	public PayloadWrapperFactory<? extends PayloadTransformer> getPayloadHandlerFactory(
			String contentType) {
		return payloadHandlersFactory.get(contentType);
	}
}
