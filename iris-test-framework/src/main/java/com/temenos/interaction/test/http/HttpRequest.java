package com.temenos.interaction.test.http;


public interface HttpRequest {

	HttpHeader headers();
	
	String payload();
}
