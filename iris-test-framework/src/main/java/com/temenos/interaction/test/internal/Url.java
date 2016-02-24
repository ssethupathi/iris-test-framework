package com.temenos.interaction.test.internal;


public interface Url {
	
	String url();
	
	Url byRel(String rel);
	
	Url baseuri(String baseUri);
	
	Url path(String path);
	
	Url queryParam(String queryParam);
	
	Url id(String id);
	
	void get();

	void post();

	void put();
}
