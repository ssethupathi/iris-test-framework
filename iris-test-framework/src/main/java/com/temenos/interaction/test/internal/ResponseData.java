package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Result;

public interface ResponseData {

	String header(String name);
	
	Result result();
	
	Payload body();

}
