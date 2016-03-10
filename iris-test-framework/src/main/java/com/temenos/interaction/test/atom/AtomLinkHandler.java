package com.temenos.interaction.test.atom;

import static com.temenos.interaction.test.atom.AtomUtil.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.xml.namespace.QName;

import org.apache.abdera.model.Element;
import org.apache.abdera.model.Link;

import com.temenos.interaction.test.PayloadHandler;
import com.temenos.interaction.test.context.ContextFactory;
import com.temenos.interaction.test.http.HttpUtil;
import com.temenos.interaction.test.internal.DefaultPayloadWrapper;
import com.temenos.interaction.test.internal.Payload;
import com.temenos.interaction.test.internal.PayloadHandlerFactory;
import com.temenos.interaction.test.internal.PayloadWrapper;

public class AtomLinkHandler {

	private Link abderaLink;
	private Payload embeddedPayload;

	public AtomLinkHandler(Link abderaLink) {
		this.abderaLink = abderaLink;
	}

	public String getTitle() {
		return abderaLink.getTitle();
	}

	public String getHref() {
		return abderaLink.getAttributeValue("href");
	}

	public String getRel() {
		return AtomUtil.extractRel(abderaLink.getAttributeValue("rel"));
	}

	public String getBaseUri() {
		try {
			return abderaLink.getBaseUri().toURL().toString();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public Payload getEmbeddedPayload() {
		return buildEmbeddedPayload();
	}

	private Payload buildEmbeddedPayload() {
		Element inlineElement = abderaLink.getFirstChild(new QName(
				NS_ODATA_METADATA, "inline"));
		if (inlineElement == null) {
			return null; // TODO null payload
		}
		Element feedElement = inlineElement.getFirstChild(new QName(NS_ATOM,
				"feed"));
		String content = "";
		if (feedElement != null) {
			content = getContent(feedElement);
		} else {
			Element entryElement = inlineElement.getFirstChild(new QName(
					NS_ATOM, "entry"));
			if (entryElement != null) {
				content = getContent(entryElement);
			}
		}
		PayloadHandlerFactory<? extends PayloadHandler> factory = ContextFactory
				.getContext()
				.entityHandlersRegistry()
				.getPayloadHandlerFactory(
						HttpUtil.removeParameter(abderaLink
								.getAttributeValue("type")));
		PayloadHandler handler = factory.entityWrapper(content);
		PayloadWrapper wrapper = new DefaultPayloadWrapper();
		wrapper.setHandler(handler);
		return wrapper;
	}

	private String getContent(Element element) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			element.writeTo(baos);
			return baos.toString("UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
