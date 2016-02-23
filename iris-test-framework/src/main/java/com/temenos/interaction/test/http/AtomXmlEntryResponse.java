package com.temenos.interaction.test.http;

import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.Result;

public class AtomXmlEntryResponse implements HttpResponse<Entry> {

	private HttpHeader header;
	private Entry feed;
	private Result result;

	public AtomXmlEntryResponse(HttpHeader header, Entry entry, Result result) {
		this.header = header;
		this.feed = entry;
		this.result = result;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public Entry body() {
		return feed;
	}

	@Override
	public Result result() {
		return result;
	}

}
