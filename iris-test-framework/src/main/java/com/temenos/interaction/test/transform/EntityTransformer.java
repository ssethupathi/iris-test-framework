package com.temenos.interaction.test.transform;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Payload;

public interface EntityTransformer<T> {
	public T transform(Entity entity);
	public Payload transform(T content);
}
