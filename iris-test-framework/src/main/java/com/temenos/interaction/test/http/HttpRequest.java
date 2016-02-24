package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Header;

public interface HttpRequest {

	Header header();
	
	String payload();
}
