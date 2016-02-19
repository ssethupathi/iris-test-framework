package com.temenos.interaction.test.transform;

import java.util.List;

import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Payload;

public class AtomFeedTransformer implements EntityTransformer<Feed> {

	private AtomTransformerHelper helper = new AtomTransformerHelper();

	@Override
	public Feed transform(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payload transform(Feed feed) {
		List<Link> links = feed.getLinks();
		return null;
	}
}
