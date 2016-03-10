package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.PayloadHandler;

public class PayloadHandlerFactory<T extends PayloadHandler> {
	private final Class<? extends PayloadHandler> handlerClass;

	private PayloadHandlerFactory(
			final Class<? extends PayloadHandler> handlerType) {
		this.handlerClass = handlerType;
	}

	public static <T extends PayloadHandler> PayloadHandlerFactory<T> createFactory(
			final Class<? extends PayloadHandler> handlerClass) {
		return new PayloadHandlerFactory<T>(handlerClass);
	}

	public PayloadHandler entityWrapper(String payload) {
		try {
			PayloadHandler handler = handlerClass.newInstance();
			handler.setPayload(payload);
			return handler;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}
}
