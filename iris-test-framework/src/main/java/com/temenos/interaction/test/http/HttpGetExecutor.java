package com.temenos.interaction.test.http;

import com.temenos.interaction.test.context.ContextFactory;
import com.temenos.interaction.test.internal.DefaultPayloadWrapper;
import com.temenos.interaction.test.internal.PayloadHandler;
import com.temenos.interaction.test.internal.PayloadHandlerFactory;
import com.temenos.interaction.test.internal.PayloadWrapper;
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
		HttpRequest request = new PayloadRequest(input.header());
		HttpClient client = HttpClientFactory.newClient();
		HttpResponse response = client.get(url, request);
		String contentType = response.headers().get("Content-Type");
		PayloadHandlerFactory factory = ContextFactory.getContext()
				.entityHandlersRegistry().getPayloadHandlerFactory(contentType);
		if (factory == null) {
			throw new IllegalStateException(
					"Content type handler factory not registered for content type '"
							+ contentType + "'");
		}
		PayloadHandler handler = factory.entityWrapper(response.body());
		PayloadWrapper wrapper = new DefaultPayloadWrapper();
		wrapper.setHandler(handler);
		return new ResponseDataImpl(response.headers(), wrapper,
				response.result());
	}
}
