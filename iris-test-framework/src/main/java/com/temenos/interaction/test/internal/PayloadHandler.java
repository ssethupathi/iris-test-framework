package com.temenos.interaction.test.internal;

import java.io.InputStream;
import java.util.List;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public interface PayloadHandler {

	boolean isCollection();
	
	List<Link> links();

	List<EntityWrapper> entities();
	
	Entity entity();
	
	void setPayload(InputStream stream);
	
}
