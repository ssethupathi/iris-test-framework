package com.temenos.interaction.test.http;


public class HttpClientFactory {

	public static HttpClient newClient() {
		return new DefaultHttpClient();
	}
}
