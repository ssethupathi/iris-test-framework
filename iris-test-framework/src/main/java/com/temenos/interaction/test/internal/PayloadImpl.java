package com.temenos.interaction.test.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Payload;

public class PayloadImpl implements Payload {

	private List<Link> links = new ArrayList<Link>();
	private List<Entity> entities = new ArrayList<Entity>();

	public PayloadImpl(List<Link> linksSrc, List<Entity> entitiesSrc) {
		this.links = new ArrayList<Link>(linksSrc.size());
		Collections.copy(this.links, linksSrc);
		this.entities = new ArrayList<>(entitiesSrc.size());
		Collections.copy(this.entities, entitiesSrc);
	}

	@Override
	public List<Link> links() {
		return Collections.unmodifiableList(links);
	}

	@Override
	public Entity entity() {
		if (entities.isEmpty()) {
			return null;
		}
		return entities.get(0);
	}

	@Override
	public List<Entity> entities() {
		return Collections.unmodifiableList(entities);
	}

}
