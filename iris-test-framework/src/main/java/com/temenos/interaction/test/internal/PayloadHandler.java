package com.temenos.interaction.test.internal;

import java.io.InputStream;
import java.util.List;

import com.temenos.interaction.test.Link;

public interface PayloadHandler {

	String getId();

	List<Link> getLinks();

	List<EntityWrapper> entities();
	
	void setContent(InputStream stream);
	
}
