package com.temenos.interaction.test.http;

import com.temenos.interaction.test.internal.RequestSessionData;
import com.temenos.interaction.test.internal.ResponseSession;

public class HttpPostExecutor implements HttpMethodExecutor {

	private String rel;
	private RequestSessionData input;

	public HttpPostExecutor(String rel, RequestSessionData input) {
		this.rel = rel;
		this.input = input;
	}
	
	@Override
	public ResponseSession execute() {
		// TODO Auto-generated method stub
		return null;
	}
}
