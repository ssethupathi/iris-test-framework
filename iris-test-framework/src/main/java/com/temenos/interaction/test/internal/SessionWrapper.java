package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.ActionableHref;
import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.Session;

public class SessionWrapper implements Session, Resettable {

	private String entityName = "";
	private String entityId = "";
	private RequestSession request;
	private SessionExecutionCallback callback;

	@Override
	public void entityId(String entityId) {
		this.entityId = entityId;
	}

	@Override
	public void entityName(String entityName) {
		this.entityName = entityName;
	}

	@Override
	public void header(String name, String value) {
		request.header(name, value);
	}

	@Override
	public ActionableHref rel(String rel) {
		RequestSessionData requestData = new RequestSessionDataImpl();
		return new LinkHrefWrapper(buildRel(rel), requestData, callback);
	}

	@Override
	public ActionableHref rel() {
		RequestSessionData requestData = new RequestSessionDataImpl();
		return new LinkHrefWrapper(buildRel(), requestData, callback);
	}

	@Override
	public void setProperty(String name, String value) {
		request.setProperty(name, value);
	}

	@Override
	public Result result() {
		validateOutCall();
		return callback.getOutput().result();
	}

	@Override
	public void entity(Entity entity) {
		request.entity(entity);
	}

	@Override
	public Payload payload() {
		validateOutCall();
		return callback.getOutput().payload();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public String header(String name) {
		validateOutCall();
		return callback.getOutput().header(name);
	}

	@Override
	public void queryParam(String value) {
		request.queryParam(value);
	}

	private void validateOutCall() {
		if (callback.getOutput() == null) {
			throw new IllegalStateException(
					"Unsupported operation on a session which is not executed yet");
		}
	}

	private String buildRel(String rel) {
		if (entityName.isEmpty()) {
			throw new IllegalStateException("");
		}
		return entityName + "('" + entityId + "')/" + rel;
	}

	private String buildRel() {
		if (entityName.isEmpty()) {
			throw new IllegalStateException("");
		}
		return entityName + "s()";
	}

	public static Session newSession() {
		RequestSession request = new RequestSessionImpl();
		return new SessionWrapper(request);
	}

	private SessionWrapper(RequestSession request) {
		this.request = request;
		this.callback = new SessionExecutionCallback();
	}

	private static class SessionExecutionCallback implements SessionCallback {

		private ResponseSession output;

		@Override
		public void setOutput(ResponseSession output) {
			this.output = output;
		}

		public ResponseSession getOutput() {
			return output;
		}
	}
}
