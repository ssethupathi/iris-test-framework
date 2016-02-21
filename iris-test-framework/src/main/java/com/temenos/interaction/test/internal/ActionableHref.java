package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Href;

public interface ActionableHref extends Href {
	void get();

	void post();

	void put();
}
