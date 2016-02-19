package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Header;

public interface HttpRequest<T> {

	Header header();
	
	T payload();
}
