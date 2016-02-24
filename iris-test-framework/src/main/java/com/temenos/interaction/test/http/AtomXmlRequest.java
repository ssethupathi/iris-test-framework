package com.temenos.interaction.test.http;


public class AtomXmlRequest implements HttpRequest {

	private HttpHeader header;
	private String entry;

	public AtomXmlRequest(HttpHeader header, String entry) {
		this.entry = entry;
	}

	public HttpHeader header() {
		return header;
	}

	@Override
	public String payload() {
		return entry;
	}
}
