package com.temenos.interaction.test.http;

import com.temenos.interaction.test.context.Context;
import com.temenos.interaction.test.context.ContextFactory;
import com.temenos.interaction.test.internal.PayloadWrapperFactory;
import com.temenos.interaction.test.internal.RequestData;
import com.temenos.interaction.test.internal.ResponseData;
import com.temenos.interaction.test.internal.ResponseDataImpl;

public class HttpGetExecutor implements HttpMethodExecutor {

	private String url;
	private RequestData input;
	private Context context;

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
		PayloadWrapperFactory factory = ContextFactory.getContext()
				.entityHandlersRegistry().getPayloadHandlerFactory(contentType);
		if (factory == null) {
			throw new IllegalStateException(
					"Content type handler factory not registered for content type '"
							+ contentType + "'");
		}
		return new ResponseDataImpl(response.headers(),
				factory.entityWrapper(response.body()), response.result());
	}
}
