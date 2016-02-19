package com.temenos.interaction.test.internal;

import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.http.AtomXmlGetRequest;
import com.temenos.interaction.test.http.HttpClient;
import com.temenos.interaction.test.http.HttpGetAtomXmlClient;
import com.temenos.interaction.test.http.HttpRequest;
import com.temenos.interaction.test.http.HttpResponse;

public class HttpGetExecutor implements HttpMethodExecutor {

	@Override
	public OutputSession execute(String rel, InputSession input) {
		HttpRequest<Feed> request = new AtomXmlGetRequest(input.header());
		HttpClient<Feed> client = new HttpGetAtomXmlClient();
		HttpResponse<Feed> response = client.get(rel, input.queryParam(),
				request);
		System.out.println(response);
		// Entry responseEntry = (Entry)response.payload();
		return null;
	}
}
