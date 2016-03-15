package com.temenos.interaction.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.temenos.interaction.test.internal.SessionWrapper;

public class TestQueryOptionsSimple {

	@Test
	public void testForEqOfId() {
		Session session = SessionWrapper.newSession();
		session.header("Content-Type", "application/atom+xml")
				.header("Accept", "application/atom+xml").url()
				.queryParam("$top=1").get();
		assertEquals(1, session.entities().all().size());
		Entity entity = session.entities().all().get(0);
		assertEquals("", entity.get(""));
	}
}
