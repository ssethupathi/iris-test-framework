package com.temenos.interaction.test.internal;

import java.io.InputStream;

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

	public PayloadHandler entityWrapper(InputStream content) {
		try {
			PayloadHandler handler = handlerClass.newInstance();
			handler.setContent(content);
			return handler;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}
}
