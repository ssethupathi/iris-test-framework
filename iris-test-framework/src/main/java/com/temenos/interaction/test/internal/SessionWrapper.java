package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Header;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.Session;

public class SessionWrapper implements Session {

	private InputSession input;
	private Entity entity;
	private SessionExecutionCallback callback;

	public SessionWrapper(InputSession input) {
		this.input = input;
		this.callback = new SessionExecutionCallback();
	}

	@Override
	public void header(String name, String value) {
		input.header(name, value);
	}

	@Override
	public Header header() {
		return input.header();
	}

	@Override
	public Link rel(String rel) {
		return new LinkWrapper(buildRel(rel), input, callback);
	}

	@Override
	public Link rel() {
		return new LinkWrapper(buildRel(), input, callback);
	}

	@Override
	public void setProperty(String name, String value) {
		input.setProperty(name, value);
	}

	@Override
	public Result result() {
		validateOutCall();
		return callback.getOutput().result();
	}

	@Override
	public void entity(Entity entity) {
		input.entity(entity);
	}

	@Override
	public Entity entity(String name) {
		if (entity == null) {
			initEntity(name);
		}
		return entity;
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
		input.queryParam(value);
	}

	@Override
	public String queryParam() {
		return input.queryParam();
	}

	private void validateOutCall() {
		if (callback.getOutput() == null) {
			throw new IllegalStateException(
					"Unsupported operation on a session which is not executed yet");
		}
	}

	private String buildRel(String relInput) {
		return entity.name() + "('" + entity.id() + "')/" + relInput;
	}

	private String buildRel() {
		return entity.name() + "s()";
	}

	private static class SessionExecutionCallback implements SessionCallback {

		private OutputSession output;

		@Override
		public void setOutput(OutputSession output) {
			this.output = output;
		}

		public OutputSession getOutput() {
			return output;
		}
	}
	
	private void initEntity(String name) {
		EntityImpl.Builder builder = new EntityImpl.Builder();
		builder.name(name);
		entity = builder.build();
	}
}
