package com.temenos.interaction.test.http;

import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.internal.AtomXmlPayload;
import com.temenos.interaction.test.internal.EntityTransformerFactory;
import com.temenos.interaction.test.internal.RequestData;
import com.temenos.interaction.test.internal.ResponseData;
import com.temenos.interaction.test.internal.ResponseDataImpl;

public class HttpGetExecutor implements HttpMethodExecutor {

	private String url;
	private RequestData input;

	public HttpGetExecutor(String rel, RequestData input) {
		this.url = rel;
		this.input = input;
	}

	@Override
	public ResponseData execute() {
		HttpRequest request = new AtomXmlFeedRequest(input.header());
		EntityTransformerFactory<Feed> clientFactory = EntityTransformerFactory
				.createFactory(Feed.class);
		HttpClient client = new HttpAbderaClient();
		HttpResponse response = client.get(url, request);
		AtomXmlPayload payload = new AtomXmlPayload(response.body());
		return new ResponseDataImpl(response.header(), payload,
				response.result());
	}
}
