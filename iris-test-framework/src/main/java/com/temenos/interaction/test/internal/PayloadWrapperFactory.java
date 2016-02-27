package com.temenos.interaction.test.internal;

import java.io.InputStream;

public class PayloadWrapperFactory<T extends PayloadTransformer> {
	private final Class<? extends PayloadTransformer> transformerClass;

	private PayloadWrapperFactory(
			final Class<? extends PayloadTransformer> transformerType) {
		this.transformerClass = transformerType;
	}

	public static <T extends PayloadTransformer> PayloadWrapperFactory<T> createFactory(
			final Class<? extends PayloadTransformer> transformerClass) {
		return new PayloadWrapperFactory<T>(transformerClass);
	}

	public PayloadWrapper entityWrapper(InputStream content) {
		try {
			PayloadWrapper wrapper = new DefaultPayloadWrapper();
			PayloadTransformer transformer = transformerClass.newInstance();
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
