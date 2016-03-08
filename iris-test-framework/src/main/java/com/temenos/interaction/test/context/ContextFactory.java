package com.temenos.interaction.test.context;

import java.util.Properties;

import com.temenos.interaction.test.atom.AtomEntryHandler;
import com.temenos.interaction.test.atom.AtomFeedHandler;
import com.temenos.interaction.test.atom.PlainTextHandler;

public class ContextFactory {

	private static Properties testConnProperties = new Properties();
	private static Properties testServProperties = new Properties();

	public static void setConnectionProperty(String name, String value) {
		testConnProperties.put(name, value);
	}

	public static void setServiceProperties(String name, String value) {
		testServProperties.put(name, value);
	}

	// TODO: make upto sys config read-only downstream
	public static Context getContext() {
		BaseConnectionConfig baseConnConfig = new BaseConnectionConfig(
				getBaseConnectionProperties());
		SystemConnectionConfig sysConnConfig = new SystemConnectionConfig(
				baseConnConfig);
		TestConnectionConfig testConnConfig = new TestConnectionConfig(
				sysConnConfig);
		BaseServiceConfig baseServConfig = new BaseServiceConfig(
				getBaseServiceProperties());
		SystemServiceConfig sysServConfig = new SystemServiceConfig(
				baseServConfig);
		return new ContextImpl(testConnConfig, sysServConfig);
	}

	// TODO: replace with the spring bean
	private static Properties getBaseConnectionProperties() {
		Properties baseConnprops = new Properties();
		baseConnprops
				.setProperty(ConnectionConfig.ENDPOINT_URI,
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc");
		baseConnprops.setProperty(ConnectionConfig.SERVICE_ROOT, "GB0010001");
		baseConnprops.setProperty(ConnectionConfig.USER_NAME, "INPUTT");
		baseConnprops.setProperty(ConnectionConfig.PASSWORD, "123456");
		return baseConnprops;
	}

	// TODO: replace with the spring bean
	private static Properties getBaseServiceProperties() {
		Properties baseServProps = new Properties();
		baseServProps.setProperty(ServiceConfig.HTTP_HEADER_CONTENT_TYPE,
				"application/atom+xml");
		baseServProps.setProperty(ServiceConfig.HTTP_HEADER_ACCEPT,
				"application/atom+xml");
		return baseServProps;
	}

	public static class ContextImpl implements Context {
		private ConnectionConfig connectionConfig;
		private ServiceConfig serviceConfig;

		private ContextImpl(ConnectionConfig connConfig,
				ServiceConfig servConfig) {
			this.connectionConfig = connConfig;
			this.serviceConfig = servConfig;
		}

		@Override
		public ConnectionConfig connectionProperties() {
			return connectionConfig;
		}

		@Override
		public ServiceConfig serviceProperties() {
			return serviceConfig;
		}

		@Override
		public ContentTypeHandlers entityHandlersRegistry() {
			ContentTypeHandlers registry = new ContentTypeHandlers();
			// registry.registerForEntity("application/atom+xml",
			// AtomEntryTransformer.class);
			registry.registerForPayload("application/atom+xml",
					AtomFeedHandler.class);
			registry.registerForPayload("text/plain",
					PlainTextHandler.class);
			registry.registerForPayload("text/html",
					PlainTextHandler.class);
			return registry;
		}
	}
}
