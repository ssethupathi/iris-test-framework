package com.temenos.interaction.test.http;

import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.internal.AtomXmlPayload;
import com.temenos.interaction.test.internal.EntityHandlerFactory;
import com.temenos.interaction.test.internal.RequestData;
import com.temenos.interaction.test.internal.ResponseData;
import com.temenos.interaction.test.internal.ResponseDataImpl;

public class HttpGetExecutor implements HttpMethodExecutor {

	private String url;
	private RequestData input;

	public HttpGetExecutor(String url, RequestData input) {
		this.url = url;
		this.input = input;
	}

	@Override
	public ResponseData execute() {
		HttpRequest request = new AtomXmlFeedRequest(input.header());
		EntityHandlerFactory<Feed> clientFactory = EntityHandlerFactory
				.createFactory(Feed.class, "");
		clientFactory.newTransformer();
		HttpClient client = HttpClientFactory.newClient();
		HttpResponse response = client.get(url, request);
		AtomXmlPayload payload = new AtomXmlPayload("");
		return new ResponseDataImpl(response.header(), payload,
				response.result());
	}
}
