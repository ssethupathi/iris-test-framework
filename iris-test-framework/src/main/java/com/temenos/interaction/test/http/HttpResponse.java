package com.temenos.interaction.test.http;

import java.io.InputStream;

import com.temenos.interaction.test.Result;

public interface HttpResponse {

	Result result();

	HttpHeader headers();

	InputStream payload();
}
