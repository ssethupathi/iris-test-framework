package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.http.HttpGetExecutor;
import com.temenos.interaction.test.http.HttpMethodExecutor;
import com.temenos.interaction.test.http.HttpPostExecutor;


public class LinkHrefWrapper implements ActionableHref {

	private String href;
	private RequestSessionData requestData;
	private SessionCallback callback;

	public LinkHrefWrapper(String href, RequestSessionData input,
			SessionCallback callback) {
		this.href = href;
		this.requestData = input;
		this.callback = callback;
	}

	@Override
	public void get() {
		HttpMethodExecutor executor = new HttpGetExecutor(href, requestData);
		ResponseSession output = executor.execute();
		callback.setResponse(output);
	}

	@Override
	public void post() {
		HttpMethodExecutor executor = new HttpPostExecutor(href, requestData);
		ResponseSession output = executor.execute();
		callback.setResponse(output);
	}

	@Override
	public void put() {
		// TODO Auto-generated method stub

	}

	@Override
	public String href() {
		return href;
	}

}
