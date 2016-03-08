package com.temenos.interaction.test.internal;

import java.util.List;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;

public interface Payload {

	boolean isCollection();
	
	List<Link> links();

	EntityWrapper entity();

	EntityWrapper entity(String id);
	
	List<EntityWrapper> entities();
}
