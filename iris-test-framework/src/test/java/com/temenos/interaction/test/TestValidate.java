package com.temenos.interaction.test;

import static org.junit.Assert.*;

import org.junit.Before;

import com.temenos.interaction.test.Session;

public class TestValidate {

	private Session session;

	@Before
	private void setUp() {
		session.entityName("verCustomer_Input");
	}

	public void test2() {
		session.url("new").post();

//		session.entity().set("Mnemonic", "C" + session.entity().id());
//		session.entity().set("ShortNameMvGroup/ShortName", "Peter");
//		session.entity().set("Sector", "foo");
		session.url("validate").post();

//		assertEquals(200, session.result().code());
//		assertEquals("NON FATAL ERROR",
//				session.entity().get("ErrorMvGroup/Error/Code"));
//		assertEquals("INVALID INPUT",
//				session.entity().get("ErrorMvGroup/Error/Text"));
	}
}
