package com.temenos.interaction.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import com.temenos.interaction.test.Session;

public class TestNewInput {

	private Session session;

	@Before
	private void setUp() {
		session.entityName("verCustomer_Input");
	}

	public void test2() {
		session.rel("new").post();
//		session.payload().entity()..set("Mnemonic", "C" + session.payload().entity().id());
//		session.payload().entity().set("ShortNameMvGroup/ShortName", "Peter");
//		session.payload().entity().set("Language", "1");
//		session.payload().entity().set("Sector", "1001");
//		session.rel("input").post();

		assertEquals("1001", session.payload().entity().get("Sector"));
	}
}
