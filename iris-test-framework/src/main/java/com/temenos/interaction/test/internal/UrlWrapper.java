package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.http.HttpGetExecutor;
import com.temenos.interaction.test.http.HttpMethodExecutor;

public class UrlWrapper implements Url {

	private String url = "";
	private String baseuri = "";
	private String path = "";
	private String queryParam = "";
	private String id = "";
	private SessionCallback<Payload> payloadCallback;

	public UrlWrapper(SessionCallback callback) {
		this.payloadCallback = callback;
	}

	public UrlWrapper(String url, SessionCallback callback) {
		this.url = url;
		this.payloadCallback = callback;
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
		HttpMethodExecutor executor = new HttpGetExecutor(url(),
				new RequestDataImpl(payloadCallback.header(), payloadCallback.entity()));
		ResponseData output = executor.execute();
		payloadCallback.setResponse(output);
	}

	@Override
	public void post() {
		// HttpMethodExecutor executor = new HttpPostExecutor(url(), null);
		// ResponseSession output = executor.execute();
		// callback.setResponse(output);
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
