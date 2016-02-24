package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.http.HttpGetExecutor;
import com.temenos.interaction.test.http.HttpMethodExecutor;
import com.temenos.interaction.test.http.HttpPostExecutor;

public class UrlWrapper implements Url {

	private String url;
	private String baseuri;
	private String path;
	private String queryParam;
	private String id;
	private SessionCallback callback;

	public UrlWrapper(SessionCallback callback) {
		this.callback = callback;
	}
	
	public UrlWrapper(String url, SessionCallback callback) {
		this.url = url;
		this.callback = callback;
	}

	@Override
	public Url byRel(String rel) {
		return null;
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
		HttpMethodExecutor executor = new HttpGetExecutor(url, null);
		ResponseSession output = executor.execute();
		callback.setResponse(output);
	}

	@Override
	public void post() {
		HttpMethodExecutor executor = new HttpPostExecutor(url, null);
		ResponseSession output = executor.execute();
		callback.setResponse(output);
	}

	@Override
	public void put() {
		// TODO Auto-generated method stub

	}

	@Override
	public String url() {
		if (url != null) {
			return url;
		} else {
			return buildUrl();
		}
	}

	private String buildUrl() {
		String idValue = "";
		if (id != null) {
			idValue = "'" + id + "'";
		}
		return baseuri + "/" + path + "(" + idValue + ")";
	}
}
