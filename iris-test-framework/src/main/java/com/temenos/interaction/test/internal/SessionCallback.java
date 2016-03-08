package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.http.HttpHeader;

public interface SessionCallback {

	HttpHeader header();

	EntityWrapper entity();

	void setResponse(ResponseData output);
	
	ResponseData getResponse();

}
