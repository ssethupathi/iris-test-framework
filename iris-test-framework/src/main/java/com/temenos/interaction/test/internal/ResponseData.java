package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Result;

public interface ResponseData<T> {

	String header(String name);
	
	Result result();
	
	T body();

}
