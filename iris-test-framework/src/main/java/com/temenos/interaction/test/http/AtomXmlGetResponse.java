package com.temenos.interaction.test.http;

import org.apache.abdera.model.Feed;

public class AtomXmlGetResponse implements HttpResponse<Feed> {

	private HttpHeader header;
	private Feed feed;

	public AtomXmlGetResponse(HttpHeader header, Feed feed) {
		this.header = header;
		this.feed = feed;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public Feed payload() {
		return feed;
	}

}
