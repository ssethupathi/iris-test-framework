package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.http.HttpHeader;

public interface RequestData {
	
	HttpHeader header();
	
	Entity entity();
}
