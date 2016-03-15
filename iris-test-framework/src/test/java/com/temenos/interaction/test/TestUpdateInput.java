package com.temenos.interaction.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.temenos.interaction.test.atom.AtomFeedHandler;
import com.temenos.interaction.test.internal.SessionWrapper;

public class TestUpdateInput {

	@Test
	public void testCreateNewEntityAndUpdate() {
		Session session = SessionWrapper.newSession();
		session.registerHandler("application/atom+xml", AtomFeedHandler.class)
				.basicAuthUser("INPUTT")
				.basicAuthPassword("12345")
				.header("Content-Type", "application/atom+xml")
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs()/new").post();

		assertEquals(201, session.result().code());

		String id = session.reuse().entity().get("CustomerCode");
		session.set("Mnemonic", "C" + id)
				.set("verCustomer_Input_Name1MvGroup/Name1",
						"Mr Robin Peterson" + id)
				.set("verCustomer_Input_ShortNameMvGroup/ShortName", "Rob" + id)
				.set("Sector", "1001").set("Gender", "MALE").set("Title", "MR")
				.set("FamilyName", "Peterson" + id).entity().links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();

		assertEquals(201, session.result().code());

		session.reuse().basicAuthUser("AUTHOR").basicAuthPassword("123456")
				.links().byRel("http://temenostech.temenos.com/rels/authorise")
				.url().put();

		assertEquals(200, session.result().code());
	}

	@Test
	public void testForConflictWithConcurrentModificationOfAResource() {

		Session setupSession = SessionWrapper.newSession();
		setupSession
				.header("Content-Type", "application/atom+xml")
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs()/new").post();
		assertEquals(201, setupSession.result().code());

		String id = setupSession.reuse().entity().get("CustomerCode");
		setupSession
				.set("Mnemonic", "C" + id)
				.set("verCustomer_Input_Name1MvGroup/Name1",
						"Mr Robin Peterson" + id)
				.set("verCustomer_Input_ShortNameMvGroup/ShortName", "Rob" + id)
				.set("Sector", "1001").set("Gender", "MALE").set("Title", "MR")
				.set("FamilyName", "Peterson" + id).entity().links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();
		assertEquals(201, setupSession.result().code());

		setupSession.reuse().basicAuthUser("AUTHOR")
				.basicAuthPassword("123456").links()
				.byRel("http://temenostech.temenos.com/rels/authorise").url()
				.put();
		assertEquals(200, setupSession.result().code());

		Session session1 = SessionWrapper.newSession();
		session1.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs('" + id + "')").get();
		String session1Etag = session1.header("ETag");

		Session session2 = SessionWrapper.newSession();
		session2.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs('" + id + "')").get();
		String session2Etag = session2.header("ETag");

		session1.reuse().header("If-Match", session1Etag)
				.header("Content-Type", "application/atom+xml")
				.set("Gender", "FEMALE").set("Title", "MS").links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();
		assertEquals(200, setupSession.result().code());

		session2.reuse().header("If-Match", session2Etag)
				.header("Content-Type", "application/atom+xml")
				.set("FamilyName", "Peter" + id).links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();

		assertEquals(
				"EB-RESOURCE.MODIFIED",
				session2.reuse().links()
						.byRel("http://temenostech.temenos.com/rels/errors")
						.embeddedPayload().entity()
						.get("Errors_ErrorsMvGroup/Code"));
	}

	@Test
	public void testForConflictOnReInputOfHeldResource() {
		Session setupSession = SessionWrapper.newSession();
		setupSession
				.header("Content-Type", "application/atom+xml")
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs()/new").post();
		assertEquals(201, setupSession.result().code());

		String id = setupSession.reuse().entity().get("CustomerCode");
		setupSession
				.set("Mnemonic", "C" + id)
				.set("verCustomer_Input_Name1MvGroup/Name1",
						"Mr Robin Peterson" + id)
				.set("verCustomer_Input_ShortNameMvGroup/ShortName", "Rob" + id)
				.set("Sector", "1001").set("Gender", "MALE").set("Title", "MR")
				.set("FamilyName", "Peterson" + id).entity().links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();
		assertEquals(201, setupSession.result().code());

		setupSession.reuse().basicAuthUser("AUTHOR")
				.basicAuthPassword("123456").links()
				.byRel("http://temenostech.temenos.com/rels/authorise").url()
				.put();
		assertEquals(200, setupSession.result().code());

		Session holdSession = SessionWrapper.newSession();
		holdSession
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs('" + id + "')").get();
		holdSession.reuse().header("If-Match", holdSession.header("ETag"))
				.header("Content-Type", "application/atom+xml")
				.set("FamilyName", "Peter" + id).entity().links()
				.byRel("http://temenostech.temenos.com/rels/hold").url().post();
		assertEquals(201, holdSession.result().code());

		Session inputSession = SessionWrapper.newSession();
		inputSession
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs('" + id + "')").get();

		inputSession.reuse().basicAuthPassword("123456")
				.basicAuthUser("AUTHOR")
				.header("If-Match", inputSession.header("ETag"))
				.header("Content-Type", "application/atom+xml")
				.set("Gender", "FEMALE").set("Title", "MS").links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();
		assertEquals(201, holdSession.result().code());
	}
}
