package com.temenos.interaction.test.http;

import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.internal.AtomXmlPayload;
import com.temenos.interaction.test.internal.RequestSessionData;
import com.temenos.interaction.test.internal.ResponseSession;
import com.temenos.interaction.test.internal.ResponseSessionImpl;

public class HttpGetExecutor implements HttpMethodExecutor {

	private String rel;
	private RequestSessionData input;

	public HttpGetExecutor(String rel, RequestSessionData input) {
		this.rel = rel;
		this.input = input;
	}

	@Override
	public ResponseSession execute() {
		HttpRequest<Feed> request = new AtomXmlFeedRequest(input.header());
		HttpClient<Feed> client = new HttpGetAtomXmlClient();
		HttpResponse<Feed> response = client.get(rel, input.queryParam(),
				request);
		AtomXmlPayload payload = new AtomXmlPayload(input.entity().name(),
				response.body());
		return new ResponseSessionImpl(response.header(), payload,
				response.result());
	}
}
