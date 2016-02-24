package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Header;

public class AtomXmlFeedRequest implements HttpRequest {

	private Header header;

	public AtomXmlFeedRequest(Header header) {
		this.header = header;
	}

	@Override
	public Header header() {
		return header;
	}

	@Override
	public String payload() {
		throw new UnsupportedOperationException(
				"No payload available as input for this type of request");
	}

}
