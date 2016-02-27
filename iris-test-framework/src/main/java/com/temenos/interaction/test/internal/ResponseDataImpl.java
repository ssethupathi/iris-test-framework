package com.temenos.interaction.test.internal;

import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.http.HttpHeader;

public class ResponseDataImpl implements ResponseData {

	private HttpHeader header;
	private Result result;
	private Payload payload;

	public ResponseDataImpl(HttpHeader header, Payload payload, Result result) {
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
		private HttpHeader header;
		private Result result;
		private Payload payload;

		public Builder header(HttpHeader header) {
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

		public ResponseData build() {
			// validate data?
			return new ResponseDataImpl(header, payload, result);
		}
	}

}
