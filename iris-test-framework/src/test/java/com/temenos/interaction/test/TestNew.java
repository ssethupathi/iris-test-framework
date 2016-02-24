package com.temenos.interaction.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import com.temenos.interaction.test.Links;
import com.temenos.interaction.test.Session;
import com.temenos.interaction.test.context.ConnectionConfig;
import com.temenos.interaction.test.context.Context;
import com.temenos.interaction.test.context.ContextFactory;

public class TestNew {

	// @Autowired
	private Context context;
	// @Autowired
	private Session session;

	@Before
	public void setUp() {
		session.entityName("verCustomer_Input");
		ContextFactory.setConnectionProperty(ConnectionConfig.ENDPOINT_URI,
				"foo");
	}

	public void testNewWithSupportedVersion() {
		session.url("new").post();
		assertEquals(201, session.result().code());
		assertEquals(2, session.payload().entity().links().size());
		assertTrue(session.payload().entity().links()
				.contains(Links.forName("input")));
	}

	public void testNewWithUnSupportedVersion() {
		session.url("new").post();

		assertEquals(404, session.result().code());
		assertEquals("NO AUTO ID SUPPORTED", session.payload().entity().get(""));
	}
}
