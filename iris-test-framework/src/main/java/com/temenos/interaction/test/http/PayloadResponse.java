package com.temenos.interaction.test.http;

import java.io.InputStream;

import com.temenos.interaction.test.Result;

public class PayloadResponse implements HttpResponse {

	private HttpHeader header;
	private InputStream feedStream;
	private Result result;

	public PayloadResponse(HttpHeader header, InputStream feedStream, Result result) {
		this.header = header;
		this.feedStream = feedStream;
		this.result = result;
	}

	@Override
	public HttpHeader headers() {
		return header;
	}

	@Override
	public InputStream body() {
		return feedStream;
	}

	@Override
	public Result result() {
		return result;
	}
}
