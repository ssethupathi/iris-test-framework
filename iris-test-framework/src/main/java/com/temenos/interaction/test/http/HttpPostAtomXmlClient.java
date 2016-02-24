//package com.temenos.interaction.test.http;
//
//import java.net.URISyntaxException;
//
//import org.apache.abdera.Abdera;
//import org.apache.abdera.model.Document;
//import org.apache.abdera.model.Entry;
//import org.apache.abdera.protocol.client.AbderaClient;
//import org.apache.abdera.protocol.client.ClientResponse;
//import org.apache.abdera.protocol.client.RequestOptions;
//import org.apache.abdera.util.EntityTag;
//import org.apache.commons.httpclient.UsernamePasswordCredentials;
//import org.apache.commons.httpclient.auth.AuthPolicy;
//import org.apache.commons.httpclient.auth.AuthScope;
//import org.apache.commons.httpclient.auth.BasicScheme;
//
//import com.temenos.interaction.test.Header;
//
//public class HttpPostAtomXmlClient implements HttpClient<Entry> {
//
//	@Override
//	public HttpResponse<Entry> get(String url, HttpRequest<Entry> request) {
//		AbderaClient client = createClient("INPUTT", "123456");
//		// TODO IllegalArgumentException is thrown here for example if query
//		// param is wrong
//		ClientResponse clientResponse = client.post(buildUri(url, ""),
//				request.payload());
//		clientResponse.getStatus();
//		HttpHeader responseHeader = buildHeader(clientResponse);
//		Document<Entry> document = clientResponse.getDocument();
//		Entry entry = document.getRoot();
//		HttpExecutionResult result = new HttpExecutionResult(
//				clientResponse.getStatus());
//		return new AtomXmlEntryResponse(responseHeader, entry, result);
//	}
//
//	private HttpHeader buildHeader(ClientResponse clientResponse) {
//		HttpHeader header = new HttpHeader();
//		for (String name : clientResponse.getHeaderNames()) {
//			header.set(name, clientResponse.getHeader(name));
//		}
//		EntityTag etag = clientResponse.getEntityTag();
//		if (etag != null) {
//			header.set("ETag", etag.getTag());
//		}
//		return header;
//	}
//
//	private RequestOptions buildOptions(Header header) {
//		RequestOptions options = new RequestOptions();
//		for (String name : header.names()) {
//			options.addHeader(name, header.get(name));
//		}
//		return options;
//	}
//
//	private AbderaClient createClient(String username, String password) {
//		AbderaClient client = new AbderaClient(new Abdera());
//		AbderaClient.registerTrustManager();
//		AbderaClient.registerScheme(AuthPolicy.BASIC, BasicScheme.class);
//		client.setAuthenticationSchemePriority(AuthPolicy.BASIC);
//		client.usePreemptiveAuthentication(false);
//		try {
//			client.addCredentials(AuthScope.ANY_HOST, AuthScope.ANY_REALM,
//					AuthScope.ANY_SCHEME, new UsernamePasswordCredentials(
//							username, password));
//		} catch (URISyntaxException e) {
//			throw new RuntimeException(e);
//		}
//		return client;
//	}
//
//	private String buildUri(String rel, String queryParam) {
//		String uri = "http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001/"
//				+ rel;
//		if (!queryParam.isEmpty()) {
//			uri += "?" + queryParam;
//		}
//		return uri;
//	}
//}
