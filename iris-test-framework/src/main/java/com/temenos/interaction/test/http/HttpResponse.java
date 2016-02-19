package com.temenos.interaction.test.http;

public interface HttpResponse<T> {

	HttpHeader header();

	T payload();
}
