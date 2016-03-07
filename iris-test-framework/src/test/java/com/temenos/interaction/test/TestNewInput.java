package com.temenos.interaction.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.temenos.interaction.test.internal.SessionWrapper;

public class TestNewInput {

	@Test
	public void test2() {
		Session session = SessionWrapper.newSession();
		session.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs()/new").post();
		assertEquals(200, session.result().code());
	}
}
