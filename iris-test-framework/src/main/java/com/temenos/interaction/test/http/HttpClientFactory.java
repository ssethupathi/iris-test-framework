package com.temenos.interaction.test.http;

public class HttpClientFactory<T> {
	private final Class<T> dataType;

	private HttpClientFactory(final Class<T> dataType) {
		this.dataType = dataType;
	}

	public static <T> HttpClientFactory<T> createFactory(final Class<T> dataType) {
		return new HttpClientFactory<T>(dataType);
	}
	
	public HttpClient<T> newClient() {
		return new HttpGetAtomXmlClient();
	}
}
