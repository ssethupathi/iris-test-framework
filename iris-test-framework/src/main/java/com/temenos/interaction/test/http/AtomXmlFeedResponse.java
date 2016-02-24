package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Result;

public class AtomXmlFeedResponse implements HttpResponse {

	private HttpHeader header;
	private String feed;
	private Result result;

	public AtomXmlFeedResponse(HttpHeader header, String feed, Result result) {
		this.header = header;
		this.feed = feed;
		this.result = result;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public String body() {
		return feed;
	}

	@Override
	public Result result() {
		return result;
	}

}
