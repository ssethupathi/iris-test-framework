package com.temenos.interaction.test.internal;

import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.transform.EntityTransformer;

public class EntityTransformerFactory<T> {

	private final Class<T> dataType;

	private EntityTransformerFactory(final Class<T> dataType) {
		this.dataType = dataType;
	}

	public static <T> EntityTransformerFactory<T> createFactory(
			final Class<T> dataType) {
		return new EntityTransformerFactory<T>(dataType);
	}

	public EntityTransformer<T> newTransformer() {
		if (dataType.equals(Entry.class)) {
			new AtomEntryHandler();
		}
		throw new UnsupportedOperationException("Media type not supported yet");
	}
}
