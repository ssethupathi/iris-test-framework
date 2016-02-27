package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Link;

public class AtomFeedHandler extends AtomTransformerHelper implements
		PayloadHandler<Feed, Entry> {

	private String feedContent;
	private Feed feed;

	public AtomFeedHandler(String feedContent) {
		this.feedContent = feedContent;
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
	public List<EntityHandler<Entry>> entityHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feed getEntityHolder() {
		// TODO Auto-generated method stub
		return null;
	}
}
