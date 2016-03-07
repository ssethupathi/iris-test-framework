package com.temenos.interaction.test.context;

import java.util.HashMap;
import java.util.Map;

import com.temenos.interaction.test.internal.PayloadHandler;
import com.temenos.interaction.test.internal.PayloadHandlerFactory;

public class ContentTypeHandlers {

	//	private Map<String, EntityHandlerFactory<? extends EntityHandler>> entityHandlersFactory;
	private Map<String, PayloadHandlerFactory<? extends PayloadHandler>> payloadHandlersFactory;

	public ContentTypeHandlers() {
//		this.entityHandlersFactory = new HashMap<String, EntityHandlerFactory<? extends EntityHandler>>();
		this.payloadHandlersFactory = new HashMap<String, PayloadHandlerFactory<? extends PayloadHandler>>();
	}

//	public void registerForEntity(String contentType,
//			final Class<? extends EntityHandler> transformerClass) {
//		entityHandlersFactory.put(contentType,
//				EntityHandlerFactory.createFactory(transformerClass));
//	}
//
//	public EntityHandlerFactory<? extends EntityHandler> getEntityHandlerFactory(
//			String contentType) {
//		return entityHandlersFactory.get(contentType);
//	}

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
