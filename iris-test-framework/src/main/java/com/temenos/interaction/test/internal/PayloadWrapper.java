package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Payload;

public interface PayloadWrapper extends Payload {

	void setHandler(PayloadHandler transformer);
}
