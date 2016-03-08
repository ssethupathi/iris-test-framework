package com.temenos.interaction.test;

import java.io.InputStream;
import java.util.List;

import com.temenos.interaction.test.internal.EntityWrapper;

public interface PayloadHandler {

	boolean isCollection();
	
	List<Link> links();

	List<EntityWrapper> entities();
	
	EntityWrapper entity();
	
	void setPayload(InputStream stream);
	
	void setParameter(String parameter);
	
}
