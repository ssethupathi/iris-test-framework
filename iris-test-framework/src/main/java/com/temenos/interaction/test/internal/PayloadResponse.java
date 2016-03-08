package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.http.HttpHeader;

public class PayloadResponse implements ResponseData {

	private HttpHeader header;
	private Result result;
	private Payload body;

	private PayloadResponse(Builder builder) {
		this.header = builder.header;
		this.result = builder.result;
		this.body = builder.body;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public Result result() {
		return result;
	}

	@Override
	public Payload body() {
		return body;
	}

	public static class Builder {
		private HttpHeader header;
		private Result result;
		private Payload body;

		public Builder(Result result) {
			this.result = result;
		}

		public Builder header(HttpHeader header) {
			this.header = header;
			return this;
		}

		public Builder body(Payload payload) {
			this.body = payload;
			return this;
		}

		public ResponseData build() {
			// validate data?
			return new PayloadResponse(this);
		}
	}
}
