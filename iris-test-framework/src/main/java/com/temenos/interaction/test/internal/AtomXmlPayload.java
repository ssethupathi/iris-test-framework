package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Payload;
import com.temenos.interaction.test.transform.AtomFeedTransformer;

public class AtomXmlPayload implements Payload {

	private String entityName;
	private AtomFeedTransformer transformer;
	private List<Entity> entities;

	public AtomXmlPayload(String entityName, Feed feed) {
		this.entityName = entityName;
		this.transformer = new AtomFeedTransformer(feed);
	}

	@Override
	public List<Link> links() {
		return transformer.getLinks();
	}

	@Override
	public Entity entity() {
		if (entities == null) {
			List<Entry> entries = transformer.getEntries();
			for (Entry entry : entries) {
				entities.add(new AtomXmlEntity(entityName, entry));
			}
		}
		return entities.get(0);
	}

	@Override
	public List<Entity> entities() {
		if (entities == null) {
			entities = new ArrayList<Entity>();
			List<Entry> entries = transformer.getEntries();
			for (Entry entry : entries) {
				entities.add(new AtomXmlEntity(entityName, entry));
			}
		}
		return entities;
	}
}
