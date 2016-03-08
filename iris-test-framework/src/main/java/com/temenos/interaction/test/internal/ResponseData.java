package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.http.HttpHeader;

public interface ResponseData {

	HttpHeader header();
	
	Result result();
	
	Payload body();

}
