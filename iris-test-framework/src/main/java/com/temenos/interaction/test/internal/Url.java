package com.temenos.interaction.test.internal;


public interface Url {
	
	String url();
	
	Url baseuri(String baseUri);
	
	Url path(String path);
	
	Url queryParam(String queryParam);
	
	Url noPayload();
	
	void get();

	void post();

	void put();
}
