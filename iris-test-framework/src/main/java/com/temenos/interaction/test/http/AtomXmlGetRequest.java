package com.temenos.interaction.test.http;

import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Header;

public class AtomXmlGetRequest implements HttpRequest<Feed> {

	private Header header;

	public AtomXmlGetRequest(Header header) {
		this.header = header;
	}

	@Override
	public Header header() {
		return header;
	}

	@Override
	public Feed payload() {
		throw new UnsupportedOperationException(
				"No payload available as input for this type of request");
	}

}
