package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Href;

public class HrefImpl implements Href {
	
	private String href;
	
	public HrefImpl(String href) {
		this.href = href;
	}

	@Override
	public String href() {
		return href;
	}
}
