package com.temenos.interaction.test.context;


public interface Context {

	ConnectionConfig connectionProperties();

	ServiceConfig serviceProperties();
	
	ContentTypeHandlers entityHandlersRegistry();
}
