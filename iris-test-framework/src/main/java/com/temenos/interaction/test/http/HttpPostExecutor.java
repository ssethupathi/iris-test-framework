//package com.temenos.interaction.test.http;
//
//import org.apache.abdera.model.Feed;
//
//import com.temenos.interaction.test.internal.AtomXmlPayload;
//import com.temenos.interaction.test.internal.RequestSessionData;
//import com.temenos.interaction.test.internal.ResponseSession;
//import com.temenos.interaction.test.internal.ResponseSessionImpl;
//
//public class HttpPostExecutor implements HttpMethodExecutor {
//
//	private String url;
//	private RequestSessionData input;
//
//	public HttpPostExecutor(String rel, RequestSessionData input) {
//		this.url = rel;
//		this.input = input;
//	}
//
//	@Override
//	public ResponseSession execute() {
//		HttpRequest<Feed> request = new AtomXmlFeedRequest(input.header());
//		HttpClient<Feed> client = new HttpGetAtomXmlClient();
//		HttpResponse<Feed> response = client.get(url, request);
//		AtomXmlPayload payload = new AtomXmlPayload(input.entity().name(),
//				response.body());
//		return new ResponseSessionImpl(response.header(), payload,
//				response.result());
//	}
//}
