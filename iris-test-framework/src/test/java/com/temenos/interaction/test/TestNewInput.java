package com.temenos.interaction.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.temenos.interaction.test.atom.AtomFeedHandler;
import com.temenos.interaction.test.internal.SessionWrapper;

public class TestNewInput {

	@Test
	public void test2() {
		Session session = SessionWrapper.newSession();
		session.registerHandler("application/atom+xml", AtomFeedHandler.class);
		
		session.header("Content-Type", "application/atom+xml")
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs()/new").post();
		
		assertEquals(201, session.result().code());

		session.reuse().set("Title", "Mr").set("GivenNames", "Peter").entity()
				.link().byRel("http://temenostech.temenos.com/rels/validate")
				.url().post();
		
		assertEquals(201, session.result().code());
		assertEquals(
				"NO SPECIFIED VALUE",
				session.reuse().entity().link()
						.byRel("http://temenostech.temenos.com/rels/errors")
						.embeddedPayload().entity()
						.get("Errors_ErrorsMvGroup/Code"));
	}
}
