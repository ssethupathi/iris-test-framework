package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Result;

public class HttpResponseImpl implements HttpResponse {

	private HttpHeader header;
	private String payload;
	private Result result;

	public HttpResponseImpl(HttpHeader header, String payload, Result result) {
		this.header = header;
		this.payload = payload;
		this.result = result;
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
	public Result result() {
		return result;
	}
	
}
