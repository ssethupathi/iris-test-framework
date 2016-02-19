package com.temenos.interaction.test.http;

import java.net.URISyntaxException;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Feed;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.abdera.protocol.client.ClientResponse;
import org.apache.abdera.protocol.client.RequestOptions;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthPolicy;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.BasicScheme;

import com.temenos.interaction.test.Header;

public class HttpGetAtomXmlClient implements HttpClient<Feed> {

	@Override
	public HttpResponse<Feed> get(String rel, String queryParam,
			HttpRequest<Feed> request) {
		AbderaClient client = createClient("INPUTT", "123456");
		RequestOptions options = buildOptions(request.header());
		// TODO IllegalArgumentException is thrown here for example if query param is wrong
		ClientResponse clientResponse = client.get(buildUri(rel, queryParam),
				options);
		HttpHeader responseHeader = buildHeader(clientResponse);
		Document<Feed> document = clientResponse.getDocument();
		Feed feed = document.getRoot();
		return new AtomXmlGetResponse(responseHeader, feed);
	}

	private HttpHeader buildHeader(ClientResponse clientResponse) {
		HttpHeader header = new HttpHeader();
		for (String name : clientResponse.getHeaderNames()) {
			header.set(name, clientResponse.getHeader(name));
		}
		return header;
	}

	private RequestOptions buildOptions(Header header) {
		RequestOptions options = new RequestOptions();
		for (String name : header.names()) {
			options.addHeader(name, header.get(name));
		}
		return options;
	}

	private AbderaClient createClient(String username, String password) {
		AbderaClient client = new AbderaClient(new Abdera());
		AbderaClient.registerTrustManager();
		AbderaClient.registerScheme(AuthPolicy.BASIC, BasicScheme.class);
		client.setAuthenticationSchemePriority(AuthPolicy.BASIC);
		client.usePreemptiveAuthentication(false);
		try {
			client.addCredentials(AuthScope.ANY_HOST, AuthScope.ANY_REALM,
					AuthScope.ANY_SCHEME, new UsernamePasswordCredentials(
							username, password));
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		return client;
	}

	private String buildUri(String rel, String queryParam) {
		String uri = "http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001/"
				+ rel;
		if (!queryParam.isEmpty()) {
			uri += "?" + queryParam;
		}
		return uri;
	}
}
