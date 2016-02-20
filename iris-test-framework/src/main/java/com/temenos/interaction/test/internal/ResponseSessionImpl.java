package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Header;
import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.Result;

public class ResponseSessionImpl implements ResponseSession {

	private Header header;
	private Result result;
	private Payload payload;

	private ResponseSessionImpl(Header header, Payload payload, Result result) {
		this.header = header;
		this.result = result;
		this.payload = payload;
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
	public Payload payload() {
		return payload;
	}

	public static class Builder {
		private Header header;
		private Result result;
		private Payload payload;

		public Builder header(Header header) {
			this.header = header;
			return this;
		}

		public Builder result(Result result) {
			this.result = result;
			return this;
		}

		public Builder payload(Payload payload) {
			this.payload = payload;
			return this;
		}

		public ResponseSession build() {
			// validate data?
			return new ResponseSessionImpl(header, payload, result);
		}
	}

}
