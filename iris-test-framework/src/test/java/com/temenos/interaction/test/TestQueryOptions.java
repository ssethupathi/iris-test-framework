package com.temenos.interaction.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.regex.Pattern;

import org.junit.Test;

import com.temenos.interaction.test.internal.SessionWrapper;

public class TestQueryOptions {

	@Test
	public void testForEqOfId() {
		// totally unconfigured example
		Session session = SessionWrapper.newSession();
		
		// totally unconfigured example
		session.url("http://www.google.com").get();
		
		session.registerForEntity("application/atom+xml", AtomEntityHandler.getInstance());
		session.contentType("application/atom+xml");  // set the content type to send to the server
		session.contentType("application/hal+json");  // ERROR, media type not handled
		session.registerForEntity("application/hal+json", HALEntityHandler.getInstance());
		session.contentType("application/hal+json").accept("application/hal+json");  // set the preferred accept type from the server (server can return something else)
		
		// URL
		session.url().post();
		session.url("http://www.google.com").get(); // full url - use as it is
		session.url().baseuri("http://somewhere.com/test/").path("Customer").post(); // build
		session.url().baseuri("http://somewhere.com/test/").path("enqCurrencyList").queryParam("filter=Id%20eq%20'GBP'").get();
		session.url().byRel("new").post();
		
//		session.entityName("enqCurrencyList");

		// what is my url
		assertEquals("http://somewhere.com/test/enqCurrencyList?filter=Id%20eq%20'GBP'", session.url());
		
		session.url(Links.byRel("new")).post();
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
		entity = session.entity("GBP");
		assertNotNull(entity);
	}
}
