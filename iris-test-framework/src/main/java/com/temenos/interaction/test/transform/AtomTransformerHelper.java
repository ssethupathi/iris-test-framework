package com.temenos.interaction.test.transform;

import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.model.Entry;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.internal.EntityImpl;

public class AtomTransformerHelper {

	public static Entity buildEntity(Entry entry) {
		EntityImpl.Builder entityBuilder = new EntityImpl.Builder();
		// entityBuilder.name(?)
		// entityBuilder.id(?)
		List<org.apache.abdera.model.Link> atomLinks = entry.getLinks();
		List<Link> entityLinks = new ArrayList<Link>();
		for (org.apache.abdera.model.Link link : atomLinks) {
			
		}
		
		return null;
	}

}
