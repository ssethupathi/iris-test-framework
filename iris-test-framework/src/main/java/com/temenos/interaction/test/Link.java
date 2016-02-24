package com.temenos.interaction.test;

public interface Link {

	String path();
	
	String rel();
	
	boolean isInline();
	
	Entity entity();
}
