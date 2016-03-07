package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.http.DefaultHttpExecutor;
import com.temenos.interaction.test.http.HttpMethod;
import com.temenos.interaction.test.http.HttpMethodExecutor;

public class UrlWrapper implements Url {

	private String url = "";
	private String baseuri = "";
	private String path = "";
	private String queryParam = "";
	private String id = "";
	private SessionCallback sessionCallback;

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
	public Url id(String id) {
		this.id = id;
		return this;
	}

	@Override
	public void get() {
		HttpMethodExecutor executor = new DefaultHttpExecutor(url(),
				new RequestDataImpl(sessionCallback.header(),
						sessionCallback.entity()));
		ResponseData output = executor.execute(HttpMethod.GET);
		sessionCallback.setResponse(output);
	}

	@Override
	public void post() {
		HttpMethodExecutor executor = new DefaultHttpExecutor(url(),
				new RequestDataImpl(sessionCallback.header(),
						sessionCallback.entity()));
		ResponseData output = executor.execute(HttpMethod.POST);
		sessionCallback.setResponse(output);
	}

	@Override
	public void put() {
		// TODO Auto-generated method stub

	}

	@Override
	public String url() {
		if (url.isEmpty()) {
			return buildUrl();
		} else {
			return completeUrlWithQueryParam(url);
		}
	}

	private String buildUrl() {
		String idValue = "";
		if (!id.isEmpty()) {
			idValue = "'" + id + "'";
		}
		return completeUrlWithQueryParam(baseuri + "/" + path + "(" + idValue
				+ ")");
	}

	private String completeUrlWithQueryParam(String url) {
		if (queryParam.isEmpty()) {
			return url;
		}
		return url + "?" + queryParam;
	}
}
