package com.temenos.interaction.test.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultHttpClient implements HttpClient {

	private Logger logger = LoggerFactory.getLogger(DefaultHttpClient.class);

	@Override
	public HttpResponse get(String url, HttpRequest request) {
		logger.info("\nURL: {}\nHEADERS: {}\nREQUEST: {}", url,
				request.headers(), request.payload());
		CloseableHttpClient client = HttpClientBuilder
				.create()
				.setDefaultCredentialsProvider(
						HttpClientHelper.getBasicCredentialProvider()).build();
		HttpGet getRequest = new HttpGet(url);
		HttpClientHelper.buildRequestHeaders(request, getRequest);
		try {
			CloseableHttpResponse httpResponse = client.execute(getRequest);
			InputStream contentStream = httpResponse.getEntity().getContent();
			HttpResponse response = new HttpResponseImpl(
					HttpClientHelper.buildResponseHeaders(httpResponse),
					IOUtils.toString(contentStream, "UTF-8"),
					HttpClientHelper.buildResult(httpResponse));
			logger.info("\nHEADERS: {}\nRESPONSE: {}", response.headers(),
					HttpClientHelper.prettyPrintXml(response.payload()));
			return response;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public HttpResponse post(String url, HttpRequest request) {
		logger.info("\nURL: {}\nHEADERS: {}\nREQUEST: {}", url,
				request.headers(), request.payload());
		CloseableHttpClient client = HttpClientBuilder
				.create()
				.setDefaultCredentialsProvider(
						HttpClientHelper.getBasicCredentialProvider()).build();
		HttpPost postRequest = new HttpPost(url);
		HttpClientHelper.buildRequestHeaders(request, postRequest);
		postRequest.setEntity(new StringEntity(request.payload(), "UTF-8"));
		try {
			CloseableHttpResponse httpResponse = client.execute(postRequest);
			InputStream contentStream = httpResponse.getEntity().getContent();
			HttpResponse response = new HttpResponseImpl(
					HttpClientHelper.buildResponseHeaders(httpResponse),
					IOUtils.toString(contentStream, "UTF-8"),
					HttpClientHelper.buildResult(httpResponse));
			logger.info("\nHEADERS: {}\nRESPONSE: {}", response.headers(),
					HttpClientHelper.prettyPrintXml(response.payload()));
			return response;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public HttpResponse put(String url, HttpRequest request) {
		logger.info("\nURL: {}\nHEADERS: {}\nREQUEST: {}", url,
				request.headers(), request.payload());
		CloseableHttpClient client = HttpClientBuilder
				.create()
				.setDefaultCredentialsProvider(
						HttpClientHelper.getBasicCredentialProvider()).build();
		HttpPut putRequest = new HttpPut(url);
		HttpClientHelper.buildRequestHeaders(request, putRequest);
		putRequest.setEntity(new StringEntity(request.payload(), "UTF-8"));
		try {
			CloseableHttpResponse httpResponse = client.execute(putRequest);
			InputStream contentStream = httpResponse.getEntity().getContent();
			HttpResponse response = new HttpResponseImpl(
					HttpClientHelper.buildResponseHeaders(httpResponse),
					IOUtils.toString(contentStream, "UTF-8"),
					HttpClientHelper.buildResult(httpResponse));
			logger.info("\nHEADERS: {}\nRESPONSE: {}", response.headers(),
					response.payload());
			return response;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
