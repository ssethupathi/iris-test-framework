package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Link;

public class LinkWrapper implements Link {

	private String rel;
	private InputSession input;
	private SessionCallback callback;

	public LinkWrapper(String rel, InputSession input, SessionCallback callback) {
		this.rel = rel;
		this.input = input;
		this.callback = callback;
	}

	@Override
	public void get() {
		HttpMethodExecutor executor = new HttpGetExecutor();
		OutputSession output = executor.execute(rel, input);
		callback.setOutput(output);
	}

	@Override
	public void post() {
		HttpMethodExecutor executor = new HttpPostExecutor();
		OutputSession output = executor.execute(rel, input);
		callback.setOutput(output);
	}

	@Override
	public void put() {
		// TODO Auto-generated method stub

	}

	@Override
	public String rel() {
		return rel;
	}

}
