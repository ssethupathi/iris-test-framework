package com.temenos.interaction.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.temenos.interaction.test.internal.SessionWrapper;

public class TestQueryOptionsSimple {

	@Test
	public void testForEqOfId() {
		Session session = SessionWrapper.newSession();
		session.accept("application/atom+xml").contentType(
				"application/atom+xml");
		session.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs").queryParam("$top=1").get();
		assertEquals(1, session.payload().entities().size());
		Entity entity = session.payload().entities().get(0);
	}
}
