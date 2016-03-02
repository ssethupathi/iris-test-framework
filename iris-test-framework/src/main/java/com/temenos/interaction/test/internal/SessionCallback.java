package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.http.HttpHeader;

public interface SessionCallback<T> {

	HttpHeader header();

	Entity entity();

	void setResponse(ResponseData<T> output);
	
	ResponseData<T> getResponse();

}
