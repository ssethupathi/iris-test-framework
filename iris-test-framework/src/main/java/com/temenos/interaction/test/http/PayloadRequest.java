package com.temenos.interaction.test.http;


public class PayloadRequest implements HttpRequest {

	private HttpHeader header;

	public PayloadRequest(HttpHeader header) {
		this.header = header;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public String payload() {
		throw new UnsupportedOperationException(
				"No payload available as input for this type of request");
	}
}
