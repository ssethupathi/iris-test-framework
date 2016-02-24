package com.temenos.interaction.test.internal;

import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.transform.AtomEntryTransformer;
import com.temenos.interaction.test.transform.EntityTransformer;

public class AtomEntryHandler implements MediaTypeHandler<Entry> {

	@Override
	public EntityTransformer<Entry> entityTransformer() {
		return new AtomEntryTransformer(null, null);
	}

}
