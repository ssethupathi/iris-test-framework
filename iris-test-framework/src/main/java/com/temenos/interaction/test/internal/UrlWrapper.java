package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.http.DefaultHttpExecutor;
import com.temenos.interaction.test.http.HttpMethod;
import com.temenos.interaction.test.http.HttpMethodExecutor;

public class UrlWrapper implements Url {

	private String url = "";
	private String baseuri = "";
	private String path = "";
	private String queryParam = "";
	private SessionCallback sessionCallback;
	private boolean noBody;

	public UrlWrapper(SessionCallback callback) {
		this.sessionCallback = callback;
	}

	public UrlWrapper(String url, SessionCallback callback) {
		this.url = url;
		this.sessionCallback = callback;
	}

	@Override
	public Url baseuri(String baseuri) {
		this.baseuri = baseuri;
		return this;
	}

	@Override
	public Url path(String path) {
		this.path = path;
		return this;
	}

	@Override
	public Url queryParam(String queryParam) {
		this.queryParam = queryParam;
		return this;
	}

	@Override
	public Url noPayload() {
		noBody = true;
		return this;
	}

	@Override
	public void get() {
		HttpMethodExecutor executor = new DefaultHttpExecutor(url(),
				new RequestDataImpl(sessionCallback.header(),
						null)); // TODO remove null
		ResponseData output = executor.execute(HttpMethod.GET);
		sessionCallback.setResponse(output);
	}

	@Override
	public void post() {
		EntityWrapper entity = sessionCallback.entity();
		if (noBody) {
			 entity = null; // TODO remove null
		}
		HttpMethodExecutor executor = new DefaultHttpExecutor(url(),
				new RequestDataImpl(sessionCallback.header(),
						entity));
		ResponseData output = executor.execute(HttpMethod.POST);
		sessionCallback.setResponse(output);
	}

	@Override
	public void put() {
		EntityWrapper entity = sessionCallback.entity();
		if (noBody) {
			 entity = null; // TODO remove null
		}
		HttpMethodExecutor executor = new DefaultHttpExecutor(url(),
				new RequestDataImpl(sessionCallback.header(),
						entity));
		ResponseData output = executor.execute(HttpMethod.PUT);
		sessionCallback.setResponse(output);
	}

	@Override
	public String url() {
		return completeUrlWithQueryParam(url.isEmpty() ? baseuri + "/" + path
				: url);
	}

	private String completeUrlWithQueryParam(String url) {
		if (queryParam.isEmpty()) {
			return url;
		}
		return url + "?" + queryParam;
	}
}
