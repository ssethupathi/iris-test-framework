package com.temenos.interaction.test.http;

import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Result;

public class AtomXmlFeedResponse implements HttpResponse<Feed> {

	private HttpHeader header;
	private Feed feed;
	private Result result;

	public AtomXmlFeedResponse(HttpHeader header, Feed feed, Result result) {
		this.header = header;
		this.feed = feed;
		this.result = result;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public Feed body() {
		return feed;
	}

	@Override
	public Result result() {
		return result;
	}

}
