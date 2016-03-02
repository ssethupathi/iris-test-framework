package com.temenos.interaction.test;

import com.temenos.interaction.test.internal.Url;

public interface Link {
	
	String id();

	String path();
	
	String rel();
	
	boolean hasEmbeddedPayload();
	
	Payload embeddedPayload();
	
	Url url();
}
