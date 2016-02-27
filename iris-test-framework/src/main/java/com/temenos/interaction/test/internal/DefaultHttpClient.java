package com.temenos.interaction.test.internal;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.temenos.interaction.test.Result;
import com.temenos.interaction.test.http.HttpClient;
import com.temenos.interaction.test.http.HttpHeader;
import com.temenos.interaction.test.http.HttpRequest;
import com.temenos.interaction.test.http.HttpResponse;
import com.temenos.interaction.test.http.HttpResult;
import com.temenos.interaction.test.http.PayloadResponse;

public class DefaultHttpClient implements HttpClient {

	@Override
	public HttpResponse get(String url, HttpRequest request) {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials("INPUTT", "123456"));
		CloseableHttpClient client = HttpClientBuilder.create()
				.setDefaultCredentialsProvider(credentialsProvider).build();
		HttpGet getRequest = new HttpGet(url);
		buildRequestHeaders(request, getRequest);
		try {
			CloseableHttpResponse httpResponse = client.execute(getRequest);
			InputStream contentStream = httpResponse.getEntity().getContent();
			return new PayloadResponse(buildResponseHeaders(httpResponse),
					contentStream, buildResult(httpResponse));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void buildRequestHeaders(HttpRequest request, HttpGet getRequest) {
		HttpHeader header = request.header();
		for (String name : header.names()) {
			getRequest.addHeader(name, header.get(name));
		}
	}

	private HttpHeader buildResponseHeaders(CloseableHttpResponse httpResponse) {
		HttpHeader header = new HttpHeader();
		for (org.apache.http.Header httpHeader : httpResponse.getAllHeaders()) {
			header.set(httpHeader.getName(), httpHeader.getValue());
		}
		return header;
	}

	private Result buildResult(CloseableHttpResponse httpResponse) {
		StatusLine statusLine = httpResponse.getStatusLine();
		return new HttpResult(statusLine.getStatusCode(),
				statusLine.getReasonPhrase());
	}
}
