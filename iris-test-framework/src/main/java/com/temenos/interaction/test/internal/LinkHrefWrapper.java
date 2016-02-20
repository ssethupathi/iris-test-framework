package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.ActionableHref;

public class LinkHrefWrapper implements ActionableHref {

	private String href;
	private RequestSessionData requestData;
	private SessionCallback callback;

	public LinkHrefWrapper(String href, RequestSessionData input, SessionCallback callback) {
		this.href = href;
		this.requestData = input;
		this.callback = callback;
	}

	@Override
	public void get() {
		HttpMethodExecutor executor = new HttpGetExecutor();
		ResponseSession output = executor.execute(href, requestData);
		callback.setOutput(output);
	}

	@Override
	public void post() {
		HttpMethodExecutor executor = new HttpPostExecutor();
		ResponseSession output = executor.execute(href, requestData);
		callback.setOutput(output);
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
