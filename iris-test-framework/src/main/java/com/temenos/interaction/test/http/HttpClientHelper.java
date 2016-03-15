package com.temenos.interaction.test.http;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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
		HttpHeader header = request.headers();
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

	public static String prettyPrintXml(String xmlDoc) {
		try {
			Source xmlInput = new StreamSource(new StringReader(xmlDoc));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			transformerFactory.setAttribute("indent-number", 4);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Exception e) {
			// Not a valid XML
			return xmlDoc;
		}
	}
}
