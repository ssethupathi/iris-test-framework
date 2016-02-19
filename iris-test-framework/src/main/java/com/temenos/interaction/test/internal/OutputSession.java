package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.Result;

public interface OutputSession {

	String header(String name);
	
	Result result();
	
	Payload payload();
}
