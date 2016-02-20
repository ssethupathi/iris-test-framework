package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Result;

public interface HttpResponse<T> {

	Result result();
	
	HttpHeader header();

	T body();
}
