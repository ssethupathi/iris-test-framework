package com.temenos.interaction.test.http;

import org.apache.abdera.model.Entry;

public class AtomXmlRequest implements HttpRequest<Entry> {

	private HttpHeader header;
	private Entry entry;

	public AtomXmlRequest(HttpHeader header, Entry entry) {
		this.entry = entry;
	}

	public HttpHeader header() {
		return header;
	}

	@Override
	public Entry payload() {
		return entry;
	}
}
