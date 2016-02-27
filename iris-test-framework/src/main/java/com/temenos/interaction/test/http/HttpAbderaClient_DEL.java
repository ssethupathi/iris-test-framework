package com.temenos.interaction.test.http;
//package com.temenos.interaction.test.http;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URISyntaxException;
//
//import org.apache.abdera.Abdera;
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
//public class HttpAbderaClient implements HttpClient {
//
//	@Override
//	public HttpResponse get(String url, HttpRequest request) {
//		AbderaClient client = createClient("INPUTT", "123456");
//		RequestOptions options = buildOptions(request.header());
//		// TODO IllegalArgumentException is thrown here for example if query
//		// param is wrong
//		ClientResponse clientResponse = client.get(url, options);
//		try {
//			String encoding = clientResponse.getCharacterEncoding();
//			if (encoding == null || encoding.isEmpty()) {
//				encoding = "UTF-8";
//			}
//			// response = IOUtils.toString(stream, encoding);
//			// JSONObject jsonObj = new JSONObject(response);
//			// System.out.println(jsonObj.get("_links"));
//			// System.out.println(response);
//			// System.out.println(new
//			// JSONObject(jsonObj.get("_links").toString()).get("self"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		HttpHeader responseHeader = buildHeader(clientResponse);
//		// Document<Element> foo = clientResponse.getDocument();
//		// Document<Feed> document = clientResponse.getDocument();
//		// Feed feed = document.getRoot();
//		HttpExecutionResult result = new HttpExecutionResult(
//				clientResponse.getStatus());
//		try {
//			HttpResponse response = new AtomXmlFeedResponse(responseHeader,
//					clientResponse.getInputStream(), result);
//			return response;
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
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
//			options.setHeader(name, header.get(name));
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
//}
