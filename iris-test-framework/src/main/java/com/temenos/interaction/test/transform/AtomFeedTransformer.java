package com.temenos.interaction.test.transform;

import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.internal.LinkImpl;

public class AtomFeedTransformer extends AtomTransformerHelper {

	private Feed feed;

	public AtomFeedTransformer(Feed feed) {
		this.feed = feed;
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

	public List<Entry> getEntries() {
		return feed.getEntries();
	}

	public Feed getFeed() {
		return feed;
	}
}
