package com.temenos.interaction.test;

import com.temenos.interaction.test.internal.Payload;

public interface Link {

	String title();

	String href();

	String rel();

	String urlStr();

	String id();

	boolean hasEmbeddedPayload();

	Payload embeddedPayload();

}
