package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.transform.EntityTransformer;

public interface MediaTypeHandler<T> {

	EntityTransformer<T> entityTransformer();
	
}
