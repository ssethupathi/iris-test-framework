package com.temenos.interaction.test.http;

public interface HttpClient {

	HttpResponse get(String url, HttpRequest request);

	HttpResponse post(String url, HttpRequest request);

	HttpResponse put(String url, HttpRequest request);
}
