package com.temenos.interaction.test.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class DefaultHttpClient implements HttpClient {

	@Override
	public HttpResponse get(String url, HttpRequest request) {
		CloseableHttpClient client = HttpClientBuilder
				.create()
				.setDefaultCredentialsProvider(
						HttpClientHelper.getBasicCredentialProvider()).build();
		HttpGet getRequest = new HttpGet(url);
		HttpClientHelper.buildRequestHeaders(request, getRequest);
		try {
			CloseableHttpResponse httpResponse = client.execute(getRequest);
			InputStream contentStream = httpResponse.getEntity().getContent();
			return new PayloadResponse(
					HttpClientHelper.buildResponseHeaders(httpResponse),
					contentStream, HttpClientHelper.buildResult(httpResponse));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public HttpResponse post(String url, HttpRequest request) {
		CloseableHttpClient client = HttpClientBuilder
				.create()
				.setDefaultCredentialsProvider(
						HttpClientHelper.getBasicCredentialProvider()).build();
		HttpPost postRequest = new HttpPost(url);
		HttpClientHelper.buildRequestHeaders(request, postRequest);
		try {
			CloseableHttpResponse httpResponse = client.execute(postRequest);
			InputStream contentStream = httpResponse.getEntity().getContent();
			return new PayloadResponse(
					HttpClientHelper.buildResponseHeaders(httpResponse),
					contentStream, HttpClientHelper.buildResult(httpResponse));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
