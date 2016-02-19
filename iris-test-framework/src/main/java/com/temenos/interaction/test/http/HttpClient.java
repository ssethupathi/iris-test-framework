package com.temenos.interaction.test.http;

public interface HttpClient<T> {

	HttpResponse<T> get(String rel, String queryParam, HttpRequest<T> request);

	// HttpResponse<T> post(HttpRequest<T> request);
	//
	// HttpResponse<T> put(HttpRequest<T> request);
}
