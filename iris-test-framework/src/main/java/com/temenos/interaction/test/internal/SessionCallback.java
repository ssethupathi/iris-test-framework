package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;

public interface SessionCallback {
	
	Header header();
	
	Entity entity();
	
	void setResponse(ResponseSession output);

}
