//package com.temenos.interaction.test.internal;
//
//import java.io.InputStream;
//
//public class EntityWrapperFactory<T extends EntityHandler> {
//	private final Class<? extends EntityHandler> handlerClass;
//
//	private EntityWrapperFactory(
//			final Class<? extends EntityHandler> handlerType) {
//		this.handlerClass = handlerType;
//	}
//
//	public static <T extends EntityHandler> EntityWrapperFactory<T> createFactory(
//			final Class<? extends EntityHandler> handlerClass) {
//		return new EntityWrapperFactory<T>(handlerClass);
//	}
//
//	public EntityWrapper entityWrapper(InputStream content) {
//		try {
//			EntityWrapper wrapper = new DefaultEntryWrapper();
//			EntityHandler handler = handlerClass.newInstance();
//			handler.setContent(content);
//			wrapper.setHandler(handler);
//			return wrapper;
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//		}
//	}
//}
