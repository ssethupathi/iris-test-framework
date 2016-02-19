package com.temenos.interaction.test.transform;

import com.temenos.interaction.test.internal.ReadOnlyContext;

public class AtomFeedTransformerFactory implements EntityTransformerFactory<AtomFeedTransformer> {

	private ReadOnlyContext context;

	public AtomFeedTransformer newTransformer() {
		if ("application/atom+xml".equals(context.mediaType())) {
			return new AtomFeedTransformer();
		}
		return null;
	}

	public static AtomFeedTransformerFactory newInstance() {
		return new AtomFeedTransformerFactory();
	}
}
