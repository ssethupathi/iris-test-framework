package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Result;

public class AtomXmlEntryResponse implements HttpResponse {

	private HttpHeader header;
	private String entry;
	private Result result;

	public AtomXmlEntryResponse(HttpHeader header, String entry, Result result) {
		this.header = header;
		this.entry = entry;
		this.result = result;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public String body() {
		return entry;
	}

	@Override
	public Result result() {
		return result;
	}

}
