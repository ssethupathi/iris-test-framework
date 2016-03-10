package com.temenos.interaction.test.http;

import com.temenos.interaction.test.Result;

public interface HttpResponse {

	Result result();

	HttpHeader headers();

	String payload();
}
