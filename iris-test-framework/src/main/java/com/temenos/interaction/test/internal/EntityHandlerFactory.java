package com.temenos.interaction.test.internal;

import org.apache.abdera.model.Entry;

public class EntityHandlerFactory<T> {
	private final Class<T> dataType;
	private final String content;

	private EntityHandlerFactory(final Class<T> dataType, String content) {
		this.dataType = dataType;
		this.content = content;
	}

	public static <T> EntityHandlerFactory<T> createFactory(
			final Class<T> dataType, String content) {
		return new EntityHandlerFactory<T>(dataType, content);
	}

	public EntityHandler<T> newTransformer() {
		if (dataType.equals(Entry.class)) {
			return null;
		}
		throw new UnsupportedOperationException("Media type not supported yet");
	}
}
