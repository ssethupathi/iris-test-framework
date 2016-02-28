package com.temenos.interaction.test.atom;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.context.ContextFactory;
import com.temenos.interaction.test.internal.DefaultEntityWrapper;
import com.temenos.interaction.test.internal.EntityHandler;
import com.temenos.interaction.test.internal.EntityHandlerFactory;
import com.temenos.interaction.test.internal.EntityWrapper;
import com.temenos.interaction.test.internal.LinkImpl;
import com.temenos.interaction.test.internal.PayloadHandler;

public class AtomFeedTransformer implements PayloadHandler {

	private Feed feed;

	public AtomFeedTransformer() {
		feed = new Abdera().newFeed();
	}

	public List<Link> getLinks() {
		List<Link> links = new ArrayList<Link>();
		List<org.apache.abdera.model.Link> abderaLinks = feed.getLinks();
		for (org.apache.abdera.model.Link abderaLink : abderaLinks) {
			String href = abderaLink.getAttributeValue("href");
			links.add(LinkImpl.newLink(href));
		}
		return links;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntityWrapper> entities() {
		List<EntityWrapper> entityWrappers = new ArrayList<EntityWrapper>();
		for (Entry entry : feed.getEntries()) {
			String contentType = "application/atom+xml";
			EntityHandlerFactory<? extends EntityHandler> factory = ContextFactory
					.getContext().entityHandlersRegistry()
					.getEntityHandlerFactory(contentType);
			EntityWrapper entityWrapper = new DefaultEntityWrapper();
			entityWrapper.setHandler(factory.handler(entry));
			entityWrappers.add(entityWrapper);
		}
		return entityWrappers;
	}

	@Override
	public void setContent(InputStream stream) {
		Document<Feed> feedDoc = new Abdera().getParser().parse(stream);
		feed = feedDoc.getRoot();
	}
}
