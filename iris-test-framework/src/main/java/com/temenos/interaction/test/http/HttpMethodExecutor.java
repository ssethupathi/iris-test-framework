package com.temenos.interaction.test.http;

import com.temenos.interaction.test.internal.ResponseData;

public interface HttpMethodExecutor {

	ResponseData execute(HttpMethod method);
}
