package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;
import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.Session;
import com.temenos.interaction.test.http.HttpHeader;

public class SessionWrapper implements Session {

	private Header header = new HttpHeader();
	private Entity entity;
	private SessionCallbackImpl callback;

	@Override
	public Url url(String url) {
		return new UrlWrapper(url, callback);
	}

	@Override
	public Url url() {
		return new UrlWrapper(callback);
	}

	@Override
	public void register(String mediaType, Object handler) {
		// TODO Auto-generated method stub
	}

	@Override
	public Session accept(String mediaType) {
		header.set("Accept", mediaType);
		return this;
	}

	@Override
	public Session contentType(String mediaType) {
		header.set("Content-type", mediaType);
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
		return callback.getResponse().payload();
	}

	@Override
	public String header(String name) {
		validateOutCall();
		return callback.getResponse().header(name);
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
		this.callback = new SessionCallbackImpl(this);
	}

	private static class SessionCallbackImpl implements SessionCallback {

		private SessionWrapper parent;
		private ResponseSession output;

		private SessionCallbackImpl(SessionWrapper parent) {
			this.parent = parent;
		}

		@Override
		public void setResponse(ResponseSession output) {
			this.output = output;
		}

		public ResponseSession getResponse() {
			return output;
		}

		@Override
		public Header header() {
			return parent.header;
		}

		@Override
		public Entity entity() {
			return parent.entity;
		}
	}
}
