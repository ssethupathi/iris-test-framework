package com.temenos.interaction.test.http;

import org.apache.http.HttpMessage;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.BasicCredentialsProvider;

import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.context.ConnectionConfig;
import com.temenos.interaction.test.context.ContextFactory;

public class HttpClientHelper {

	public static void buildRequestHeaders(HttpRequest request,
			HttpMessage message) {
		HttpHeader header = request.header();
		for (String name : header.names()) {
			message.addHeader(name, header.get(name));
		}
	}

	public static HttpHeader buildResponseHeaders(
			CloseableHttpResponse httpResponse) {
		HttpHeader header = new HttpHeader();
		for (org.apache.http.Header httpHeader : httpResponse.getAllHeaders()) {
			header.set(httpHeader.getName(), httpHeader.getValue());
		}
		return header;
	}

	public static Result buildResult(CloseableHttpResponse httpResponse) {
		StatusLine statusLine = httpResponse.getStatusLine();
		return new HttpResult(statusLine.getStatusCode(),
				statusLine.getReasonPhrase());
	}

	public static CredentialsProvider getBasicCredentialProvider() {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(
				AuthScope.ANY,
				new UsernamePasswordCredentials(ContextFactory.getContext()
						.connectionProperties()
						.getValue(ConnectionConfig.USER_NAME), ContextFactory
						.getContext().connectionProperties()
						.getValue(ConnectionConfig.PASSWORD)));
		return credentialsProvider;
	}
}
