package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.context.ContextFactory;
import com.temenos.interaction.test.internal.DefaultEntityWrapper;
import com.temenos.interaction.test.internal.EntityHandler;
import com.temenos.interaction.test.internal.EntityHandlerFactory;
import com.temenos.interaction.test.internal.EntityResponse;
import com.temenos.interaction.test.internal.EntityWrapper;
import com.temenos.interaction.test.internal.RequestData;
import com.temenos.interaction.test.internal.ResponseData;

public class HttpPostExecutor implements HttpMethodExecutor {

	private String url;
	private RequestData input;

	public HttpPostExecutor(String url, RequestData input) {
		this.url = url;
		this.input = input;
	}

	@Override
	public ResponseData<Entity> execute() {
		HttpRequest request = new PayloadRequest(input.header());
		HttpClient client = HttpClientFactory.newClient();
		HttpResponse response = client.post(url, request);
		String contentType = response.headers().get("Content-Type");
		EntityHandlerFactory factory = ContextFactory.getContext()
				.entityHandlersRegistry().getEntityHandlerFactory(contentType);
		if (factory == null) {
			throw new IllegalStateException(
					"Content type handler factory not registered for content type '"
							+ contentType + "'");
		}
		EntityHandler handler = factory.handler(response.body());
		EntityWrapper entity = new DefaultEntityWrapper();
		entity.setHandler(handler);
		EntityResponse.Builder responseBuilder = new EntityResponse.Builder(
				response.result());
		responseBuilder.header(response.headers());
		responseBuilder.body(entity);
		return responseBuilder.build();
	}

}
