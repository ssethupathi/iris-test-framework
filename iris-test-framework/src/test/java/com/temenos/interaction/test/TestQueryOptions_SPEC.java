package com.temenos.interaction.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.regex.Pattern;

import org.junit.Test;

import com.temenos.interaction.test.internal.SessionWrapper;

public class TestQueryOptions_SPEC {

	@Test
	public void testForEqOfId() {
		Session session = SessionWrapper.newSession();
		session.registerForEntity("application/atom+xml", AtomEntityHandler.getInstance());
		session.contentType("application/atom+xml");  // set the content type to send to the server
		session.contentType("application/hal+json");  // ERROR, media type not handled
		session.registerForEntity("application/hal+json", HALEntityHandler.getInstance());
		session.contentType("application/hal+json");  // set the content type to send to the server
		session.accept("application/hal+json");  // set the preferred accept type from the server (server can return something else)
		session.baseuri("http://somewhere.com/test/").path("enqCurrencyList");
//		session.entityName("enqCurrencyList");
		session.queryParam("filter=Id%20eq%20'GBP'");
		// what is my url
		assertEquals("http://somewhere.com/test/enqCurrencyList?filter=Id%20eq%20'GBP'", session.url());
		
		session.get().url(By.url("new")).post();
		session.url(By.id("test_link")).entity().set("name", "mydata").post();
		session.url(By.byTitle(Pattern.compile("AuthoriseLink"))).post();
		// assert status
		assertEquals(200, session.result().code());
		// assert links
		Entity entity = session.payload().entity();
		Collection<Link> links = entity.links();
//		entity.link("Errors").entity().get("ErrorsMvGroup/Error/Code");
		assertFalse(links.isEmpty());
		
		// assert properties
		assertEquals(1, session.payload().entities().size());
		assertEquals("GBP", session.payload().entity().get("Id"));
		assertTrue(session.payload().entity().get("CcyNameMvGroup/CcyName")
				.contains("Pound"));
		
		// some tests on specific entities obtained in id
		entity = session.payload().entity("GBP");
		assertNotNull(entity);
	}
}
