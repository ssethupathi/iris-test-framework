package com.temenos.interaction.test;

import com.temenos.interaction.test.internal.Payload;
import com.temenos.interaction.test.internal.Url;

public interface Link {
	
	String title();

	String href();
	
	String rel();
	
	boolean hasEmbeddedPayload();
	
	Payload embeddedPayload();
	
	Url url();
}
