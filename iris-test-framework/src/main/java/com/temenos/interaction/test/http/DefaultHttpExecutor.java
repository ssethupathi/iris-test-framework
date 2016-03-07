package com.temenos.interaction.test.http;

import com.temenos.interaction.test.context.ContextFactory;
import com.temenos.interaction.test.internal.DefaultPayloadWrapper;
import com.temenos.interaction.test.internal.PayloadHandler;
import com.temenos.interaction.test.internal.PayloadHandlerFactory;
import com.temenos.interaction.test.internal.PayloadResponse;
import com.temenos.interaction.test.internal.PayloadWrapper;
import com.temenos.interaction.test.internal.RequestData;
import com.temenos.interaction.test.internal.ResponseData;

public class DefaultHttpExecutor implements HttpMethodExecutor {

	private String url;
	private RequestData input;

	public DefaultHttpExecutor(String url, RequestData input) {
		this.url = url;
		this.input = input;
	}

	@Override
	public ResponseData execute(HttpMethod method) {
		switch (method) {
		case GET:
			return get();

		case POST:
			return post();
		}
		throw new RuntimeException("Http method '" + method + "' not supported");

	}

	private ResponseData get() {
		HttpRequest request = new PayloadRequest(input.header());
		HttpClient client = HttpClientFactory.newClient();
		HttpResponse response = client.get(url, request);
		return buildResponse(response);
	}

	private ResponseData post() {
		HttpRequest request = new PayloadRequest(input.header());
		HttpClient client = HttpClientFactory.newClient();
		HttpResponse response = client.post(url, request);
		return buildResponse(response);
	}

	private ResponseData buildResponse(HttpResponse response) {
		String contentType = response.headers().get("Content-Type");
		PayloadHandlerFactory factory = ContextFactory.getContext()
				.entityHandlersRegistry().getPayloadHandlerFactory(contentType);
		if (factory == null) {
			throw new IllegalStateException(
					"Content type handler factory not registered for content type '"
							+ contentType + "'");
		}
		PayloadHandler handler = factory.entityWrapper(response.body());
		PayloadWrapper payload = new DefaultPayloadWrapper();
		payload.setHandler(handler);
		PayloadResponse.Builder responseBuilder = new PayloadResponse.Builder(
				response.result());
		responseBuilder.header(response.headers());
		responseBuilder.body(payload);
		return responseBuilder.build();
	}
}
