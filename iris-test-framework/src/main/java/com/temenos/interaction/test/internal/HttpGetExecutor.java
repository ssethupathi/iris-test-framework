package com.temenos.interaction.test.internal;

import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.http.AtomXmlGetRequest;
import com.temenos.interaction.test.http.HttpClient;
import com.temenos.interaction.test.http.HttpGetAtomXmlClient;
import com.temenos.interaction.test.http.HttpRequest;
import com.temenos.interaction.test.http.HttpResponse;

public class HttpGetExecutor implements HttpMethodExecutor {

	@Override
	public ResponseSession execute(String rel, RequestSessionData input) {
		HttpRequest<Feed> request = new AtomXmlGetRequest(input.header());
		HttpClient<Feed> client = new HttpGetAtomXmlClient();
		HttpResponse<Feed> response = client.get(rel, input.queryParam(),
				request);
		AtomXmlPayload payload = new AtomXmlPayload(input.entity().name(),
				response.body());
		return new ResponseSessionImpl(
				response.header(), payload, response.result());
	}
}
