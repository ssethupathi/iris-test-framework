package com.temenos.interaction.test.http;


public interface HttpRequest {

	HttpHeader header();
	
	String payload();
}
