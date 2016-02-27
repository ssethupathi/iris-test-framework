package com.temenos.interaction.test.http;

import com.temenos.interaction.test.internal.DefaultHttpClient;

public class HttpClientFactory {

	public static HttpClient newClient() {
		return new DefaultHttpClient();
	}
}
