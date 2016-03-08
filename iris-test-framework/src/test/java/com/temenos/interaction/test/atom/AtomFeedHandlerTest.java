package com.temenos.interaction.test.atom;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.temenos.interaction.test.Entity;
import com.temenos.interaction.test.Link;
import com.temenos.interaction.test.internal.EntityWrapper;

public class AtomFeedHandlerTest {

	private AtomFeedHandler transformer = new AtomFeedHandler();

	@Test
	public void testIsCollectionForTrue() {
		transformer.setPayload(AtomFeedHandler.class
				.getResourceAsStream("/atom_feed_with_single_entry.txt"));
		assertTrue(transformer.isCollection());
	}

	@Test
	public void testIsCollectionForFalse() {
		transformer.setPayload(AtomFeedHandler.class
				.getResourceAsStream("/atom_entry_with_xml_content.txt"));
		assertFalse(transformer.isCollection());
	}

	@Test
	public void testSetPayloadForNull() {
		try {
			transformer.setPayload(null);
			fail("Should have thrown IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testSetPayloadForInvalidXmlContent() {
		try {
			transformer
					.setPayload(IOUtils
							.toInputStream(
									"<some><valid><xml><but><invalid><atom-xml>foo</atom-xml></invalid></but></xml></valid></some>",
									"UTF-8"));
			fail("Should have thrown IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testSetPayloadForInvalidTextContent() {
		try {
			transformer.setPayload(IOUtils.toInputStream("foo", "UTF-8"));
			fail("Should have thrown IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testSetPayloadForValidFeed() {
		transformer.setPayload(AtomFeedHandler.class
				.getResourceAsStream("/atom_feed_with_single_entry.txt"));
		assertTrue(transformer.isCollection());
	}

	@Test
	public void testGetLinks() {
		transformer.setPayload(AtomFeedHandler.class
				.getResourceAsStream("/atom_feed_with_single_entry.txt"));
		List<Link> links = transformer.links();
		assertEquals(2, links.size());

		// first 'self' link
		Link firstLink = links.get(0);
		assertEquals("self", firstLink.rel());
		assertEquals("Customers()", firstLink.href());
		assertFalse(firstLink.hasEmbeddedPayload());

		// second 'new' link
		Link secondLink = links.get(1);
		assertEquals("http://mybank/rels/new", secondLink.rel());
		assertEquals("Customers()/new", secondLink.href());
		assertFalse(secondLink.hasEmbeddedPayload());
	}

	@Test
	public void testEntities() {
		transformer.setPayload(AtomFeedHandler.class
				.getResourceAsStream("/atom_feed_with_single_entry.txt"));
		List<EntityWrapper> entities = transformer.entities();
		assertEquals(1, entities.size());
		Entity entity = entities.get(0);
		assertEquals(4, entity.links().size());
	}
}
