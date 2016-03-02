package com.temenos.interaction.test.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Links;
import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.Session;
import com.temenos.interaction.test.context.ContextFactory;
import com.temenos.interaction.test.http.HttpHeader;

public class SessionWrapper implements Session {

	private HttpHeader header;
	private Map<String, String> properties;
	private Entity entity;
	private PayloadCallbackImpl callback;

	@Override
	public Url url(String url) {
		return new UrlWrapper(url, callback);
	}

	@Override
	public Url url() {
		return new UrlWrapper(callback);
	}

	@Override
	public void registerEntityHandler(String contentType,
			Class<? extends EntityHandler> handler) {
		ContextFactory.getContext().entityHandlersRegistry()
				.registerForEntity(contentType, handler);

	}

	@Override
	public Session header(String name, String value) {
		header.set(name, value);
		return this;
	}

	@Override
	public Session header(String name, String... values) {
		header.set(name, null);
		return this;
	}

	@Override
	public Session set(String propertyName, String propertyValue) {
		properties.put(propertyName, propertyValue);
		return this;
	}

	@Override
	public Session use() {
		// TODO deep copy of sort
		entity = callback.getResponse().body().entity();
		return this;
	}

	@Override
	public Session clear() {
		initialiseToDefaults();
		return this;
	}

	@Override
	public Result result() {
		validateOutCall();
		return callback.getResponse().result();
	}

	@Override
	public Payload payload() {
		validateOutCall();
		return callback.getResponse().body();
	}

	@Override
	public String header(String name) {
		validateOutCall();
		return callback.getResponse().header(name);
	}

	@Override
	public Links links() {
		return Links.create(callback.getResponse().body().links());
	}

	private void validateOutCall() {
		if (callback.getResponse() == null) {
			throw new IllegalStateException(
					"Unsupported operation on a session which is not executed yet");
		}
	}

	public static Session newSession() {
		return new SessionWrapper();
	}

	private SessionWrapper() {
		initialiseToDefaults();

	}

	private void initialiseToDefaults() {
		header = new HttpHeader();
		properties = new HashMap<String, String>();
		entity = null;
		this.callback = new PayloadCallbackImpl(this);
	}

	private static class PayloadCallbackImpl implements
			SessionCallback<Payload> {

		private SessionWrapper parent;
		private ResponseData<Payload> output;

		private PayloadCallbackImpl(SessionWrapper parent) {
			this.parent = parent;
		}

		@Override
		public void setResponse(ResponseData<Payload> output) {
			this.output = output;
		}

		public ResponseData<Payload> getResponse() {
			return output;
		}

		@Override
		public HttpHeader header() {
			return parent.header;
		}

		@Override
		public Entity entity() {
			// TODO build/modify the entity and return
			return parent.entity;
		}
	}
}
