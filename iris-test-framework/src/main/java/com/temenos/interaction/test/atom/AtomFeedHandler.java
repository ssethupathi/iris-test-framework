package com.temenos.interaction.test.atom;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.ParseException;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.PayloadHandler;
import com.temenos.interaction.test.internal.DefaultEntityWrapper;
import com.temenos.interaction.test.internal.EntityWrapper;
import com.temenos.interaction.test.internal.LinkImpl;
import com.temenos.interaction.test.internal.NullEntityWrapper;

public class AtomFeedHandler implements PayloadHandler {

	private AtomEntryHandler entityTransformer = new AtomEntryHandler();
	private boolean isCollection;
	private Feed feed;
	private String parameter;

	public AtomFeedHandler() {
		feed = new Abdera().newFeed();
	}

	@Override
	public boolean isCollection() {
		return isCollection;
	}

	public List<Link> links() {
		List<Link> links = new ArrayList<Link>();
		List<org.apache.abdera.model.Link> abderaLinks = feed.getLinks();
		for (org.apache.abdera.model.Link abderaLink : abderaLinks) {
			links.add(LinkImpl.newLink(AtomUtil.getBaseUrl(feed),
					AtomUtil.extractRel(abderaLink.getAttributeValue("rel")),
					abderaLink.getAttributeValue("href")));
		}
		return links;
	}

	@Override
	public List<EntityWrapper> entities() {
		List<EntityWrapper> entityWrappers = new ArrayList<EntityWrapper>();
		for (Entry entry : feed.getEntries()) {
			EntityWrapper entityWrapper = new DefaultEntityWrapper();
			AtomEntryHandler entryHandler = new AtomEntryHandler();
			entryHandler.setEntry(entry);
			entityWrapper.setHandler(entryHandler);
			entityWrappers.add(entityWrapper);
		}
		return entityWrappers;
	}

	@Override
	public void setPayload(InputStream stream) {
		if (stream == null) {
			throw new IllegalArgumentException("Payload input stream is null");
		}
		Document<Element> payloadDoc = null;
		try {
			payloadDoc = new Abdera().getParser().parse(stream);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Unexpected payload for media type '" + AtomUtil.MEDIA_TYPE
							+ "'.", e);
		}
		QName rootElementQName = payloadDoc.getRoot().getQName();
		if (new QName(AtomUtil.NS_ATOM, "feed").equals(rootElementQName)) {
			feed = (Feed) payloadDoc.getRoot();
			isCollection = true;
		} else if (new QName(AtomUtil.NS_ATOM, "entry")
				.equals(rootElementQName)) {
			entityTransformer.setEntry((Entry) payloadDoc.getRoot());
			isCollection = false;
		} else {
			throw new IllegalArgumentException(
					"Unexpected payload for media type '" + AtomUtil.MEDIA_TYPE
							+ "'. Payload [" + payloadDoc.getRoot().toString()
							+ "]");
		}
	}

	@Override
	public EntityWrapper entity() {
		if (!isCollection) {
			EntityWrapper wrapper = new DefaultEntityWrapper();
			wrapper.setHandler(entityTransformer);
			return wrapper;
		}
		return new NullEntityWrapper();
	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}
