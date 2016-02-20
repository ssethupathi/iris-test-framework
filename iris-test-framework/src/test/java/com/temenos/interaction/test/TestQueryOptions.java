package com.temenos.interaction.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.temenos.interaction.test.internal.SessionWrapper;

public class TestQueryOptions {

	@Test
	public void testForEqOfId() {
		Session session = SessionWrapper.newSession();
		session.entityName("enqCurrencyList");
		session.queryParam("filter=Id%20eq%20'GBP'");
		session.rel().get();
		assertEquals(1, session.payload().entities().size());
		assertEquals("GBP", session.payload().entity().get("Id"));
		assertTrue(session.payload().entity().get("CcyNameMvGroup/CcyName")
				.contains("Pound"));
	}
}
