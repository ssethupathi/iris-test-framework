package com.temenos.interaction.test.internal;


public interface PayloadWrapper extends Payload {

	void setHandler(PayloadHandler transformer);
}
