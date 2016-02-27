package com.temenos.interaction.test.internal;

import java.io.InputStream;

public class EntityWrapperFactory<T extends EntityTransformer> {
	private final Class<? extends EntityTransformer> transformerClass;

	private EntityWrapperFactory(
			final Class<? extends EntityTransformer> transformerType) {
		this.transformerClass = transformerType;
	}

	public static <T extends EntityTransformer> EntityWrapperFactory<T> createFactory(
			final Class<? extends EntityTransformer> transformerClass) {
		return new EntityWrapperFactory<T>(transformerClass);
	}

	public EntityWrapper entityWrapper(InputStream content) {
		try {
			EntityWrapper wrapper = new DefaultEntryWrapper();
			EntityTransformer transformer = transformerClass.newInstance();
			transformer.setContent(content);
			wrapper.setTransformer(transformer);
			return wrapper;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}
}
