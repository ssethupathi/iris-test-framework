package com.temenos.interaction.test.atom;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.temenos.interaction.test.Link;

public class AtomEntryTransformerTest {

	private AtomEntryTransformer transformer = new AtomEntryTransformer();

	@Test
	public void testGetLinks() {
		transformer.setContent(AtomEntryTransformer.class
				.getResourceAsStream("/atom_entry_with_xml_content.txt"));
		List<Link> links = transformer.getLinks();
		assertEquals(4, links.size());

		// first 'self' link
		Link firstLink = links.get(0);
		assertEquals("self", firstLink.rel());
		assertEquals("Customers('100974')", firstLink.href());
		assertFalse(firstLink.hasEmbeddedPayload());

		// second 'see' link
		Link secondLink = links.get(1);
		assertEquals("http://mybank/rels/see", secondLink.rel());
		assertEquals("Customers('100974')/see", secondLink.href());
		assertFalse(secondLink.hasEmbeddedPayload());

		// third 'see' link
		Link thirdLink = links.get(2);
		assertEquals("http://mybank/rels/input", thirdLink.rel());
		assertEquals("Customers('100974')/input", thirdLink.href());
		assertFalse(thirdLink.hasEmbeddedPayload());

		// fourth 'see' link
		Link fourthLink = links.get(3);
		assertEquals("http://mybank/rels/review", fourthLink.rel());
		assertEquals("Customers('100974')/review", fourthLink.href());
		assertFalse(fourthLink.hasEmbeddedPayload());
	}

	@Test
	public void testGetId() {
		transformer.setContent(AtomEntryTransformer.class
				.getResourceAsStream("/atom_entry_with_xml_content.txt"));
		assertEquals("100974", transformer.getId());
	}

	@Test
	public void testGetCount() {
		transformer.setContent(AtomEntryTransformer.class
				.getResourceAsStream("/atom_entry_with_xml_content.txt"));
		assertEquals(1, transformer.getCount("GivenNames"));
		assertEquals(2, transformer.getCount("Customer_LegalIdGroup/LegalId"));
	}

	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetContent() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEntry() {
		fail("Not yet implemented");
	}

}
