package com.temenos.interaction.test.http;

public interface HttpClient {

	HttpResponse get(String url, HttpRequest request);

	// HttpResponse<T> post(HttpRequest<T> request);
	//
	// HttpResponse<T> put(HttpRequest<T> request);
}
