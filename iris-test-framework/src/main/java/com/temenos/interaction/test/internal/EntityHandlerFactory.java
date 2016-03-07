//package com.temenos.interaction.test.internal;
//
//import java.io.InputStream;
//
//
//public class EntityHandlerFactory<T extends EntityHandler> {
//	private final Class<? extends EntityHandler> handlerClass;
//
//	private EntityHandlerFactory(
//			final Class<? extends EntityHandler> handlerType) {
//		this.handlerClass = handlerType;
//	}
//
//	public static <T extends EntityHandler> EntityHandlerFactory<T> createFactory(
//			final Class<? extends EntityHandler> handlerClass) {
//		return new EntityHandlerFactory<T>(handlerClass);
//	}
//
//	public EntityHandler handler(InputStream content) {
//		try {
//			EntityWrapper wrapper = new DefaultEntityWrapper();
//			EntityHandler handler = handlerClass.newInstance();
//			handler.setContent(content);
//			wrapper.setHandler(handler);
//			return handler;
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		}
//	}
//}
