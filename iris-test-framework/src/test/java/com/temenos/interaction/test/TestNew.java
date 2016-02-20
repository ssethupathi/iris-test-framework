package com.temenos.interaction.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import com.temenos.interaction.test.Context;
import com.temenos.interaction.test.Links;
import com.temenos.interaction.test.Session;

public class TestNew {

	// @Autowired
	private Context context;
	// @Autowired
	private Session session;

	@Before
	public void setUp() {
		session.entityName("verCustomer_Input");
	}

	public void testNewWithSupportedVersion() {
		session.rel("new").post();
		assertEquals(200, session.result().code());
		assertEquals(2, session.payload().entity().links().size());
		assertTrue(session.payload().entity().links()
				.contains(Links.forName("input")));
	}

	public void testNewWithUnSupportedVersion() {
		session.rel("new").post();

		assertEquals(404, session.result().code());
		assertEquals("NO AUTO ID SUPPORTED", session.payload().entity().get(""));
	}
}
