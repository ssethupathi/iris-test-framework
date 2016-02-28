package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.http.HttpHeader;

public class EntityResponse implements ResponseData<Entity> {

	private HttpHeader header;
	private Result result;
	private Entity body;

	private EntityResponse(Builder builder) {
		this.header = builder.header;
		this.result = builder.result;
		this.body = builder.body;
	}

	@Override
	public String header(String name) {
		return header.get(name);
	}

	@Override
	public Result result() {
		return result;
	}

	@Override
	public Entity body() {
		return body;
	}

	public static class Builder {
		private HttpHeader header;
		private Result result;
		private Entity body;

		public Builder(Result result) {
			this.result = result;
		}

		public Builder header(HttpHeader header) {
			this.header = header;
			return this;
		}

		public Builder body(Entity body) {
			this.body = body;
			return this;
		}

		public ResponseData<Entity> build() {
			return new EntityResponse(this);
		}
	}
}
