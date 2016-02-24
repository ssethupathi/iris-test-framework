package com.temenos.interaction.test.internal;

import java.util.regex.Pattern;

import com.temenos.interaction.test.Link;

public interface LinkHolder {
	
	public Link byRel(String rel);
	public Link byPattern(Pattern pattern);
}
