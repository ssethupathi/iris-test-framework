package com.temenos.interaction.test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.temenos.interaction.test.internal.SessionWrapper;

public class TestQueryOptions {

	@Test
	public void testForEqOfId() {
		Session session = SessionWrapper.newSession();
		session.entityName("enqCurrencyList");
		session.queryParam("filter=Id%20eq%20'GBP'");
		session.rel().get();
		
		// assert status
		assertEquals(200, session.result().code());
		// assert links
		Entity entity = session.payload().entity();
		Collection<Link> links = entity.links();
//		entity.link("Errors").entity().get("ErrorsMvGroup/Error/Code");
		assertFalse(links.isEmpty());
		
		// assert properties
		assertEquals(1, session.payload().entities().size());
		assertEquals("GBP", session.payload().entity().get("Id"));
		assertTrue(session.payload().entity().get("CcyNameMvGroup/CcyName")
				.contains("Pound"));
		
		// some tests on specific entities obtained in id
		entity = session.payload().entity("GBP");
		assertNotNull(entity);
	}
}
