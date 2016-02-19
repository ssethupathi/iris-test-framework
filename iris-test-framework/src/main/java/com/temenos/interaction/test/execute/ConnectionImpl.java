package com.temenos.interaction.test.execute;

public class ConnectionImpl implements Connection {
	private String uri;
	private String user;
	private String password;

	public ConnectionImpl(String uri, String user, String password) {
		this.uri = uri;
		this.user = user;
		this.password = password;
	}

	@Override
	public String uri() {
		return uri;
	}

	@Override
	public String user() {
		return user;
	}

	@Override
	public String password() {
		return password;
	}
}
