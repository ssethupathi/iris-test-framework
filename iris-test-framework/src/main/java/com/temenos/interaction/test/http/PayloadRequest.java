package com.temenos.interaction.test.http;

public class PayloadRequest implements HttpRequest {

	private HttpHeader header;
	private String payload;

	public PayloadRequest(HttpHeader header, String payload) {
		this.header = header;
		this.payload = payload;
	}

	public PayloadRequest(HttpHeader header) {
		this.header = header;
		this.payload = "";
	}

	@Override
	public HttpHeader headers() {
		return header;
	}

	@Override
	public String payload() {
		return payload;
	}

	@Override
	public String toString() {
		return "PayloadRequest [header=" + header + ", payload=" + payload
				+ "]";
	}
}
