package com.temenos.interaction.test.context;

import com.temenos.interaction.test.internal.ContentTypeHandlers;

public interface Context {

	ConnectionConfig connectionProperties();

	ServiceConfig serviceProperties();
	
	ContentTypeHandlers entityHandlersRegistry();
}
