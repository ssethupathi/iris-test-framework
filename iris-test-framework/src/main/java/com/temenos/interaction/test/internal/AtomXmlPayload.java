package com.temenos.interaction.test.internal;

import java.util.List;

import org.apache.abdera.model.Feed;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.Payload;

public class AtomXmlPayload implements Payload {
	
	private Feed feed;
	private List<Link> links;

	@Override
	public List<Link> links() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity entity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entity> entities() {
		// TODO Auto-generated method stub
		return null;
	}

}
