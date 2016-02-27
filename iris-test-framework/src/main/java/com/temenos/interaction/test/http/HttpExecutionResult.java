package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Result;

public class HttpExecutionResult implements Result {

	private int statusCode = -1;

	public HttpExecutionResult(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public int code() {
		return statusCode;
	}
}
