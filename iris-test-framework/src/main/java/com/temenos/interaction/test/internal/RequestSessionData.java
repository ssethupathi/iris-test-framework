package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;

public interface RequestSessionData {

	Header header();
	
	String queryParam();
	
	Entity entity();
}
