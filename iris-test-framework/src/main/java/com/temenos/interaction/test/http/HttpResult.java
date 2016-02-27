package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Result;

public class HttpResult implements Result {

	private int code;
	private String reason;

	public HttpResult(int statusCode, String reason) {
		this.code = statusCode;
		this.reason = reason;
	}

	@Override
	public int code() {
		return code;
	}

	@Override
	public String reason() {
		return reason;
	}
}
