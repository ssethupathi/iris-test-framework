package com.temenos.interaction.test.internal;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Link;

public class AtomFeedTransformer implements
		PayloadTransformer {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContent(InputStream stream) {
//		feed.set
	}
}
