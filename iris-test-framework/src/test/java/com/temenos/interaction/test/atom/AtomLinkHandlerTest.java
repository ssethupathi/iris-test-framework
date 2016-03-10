package com.temenos.interaction.test.atom;

import static org.junit.Assert.*;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Link;
import org.junit.Before;
import org.junit.Test;

import com.temenos.interaction.test.internal.Payload;

public class AtomLinkHandlerTest {

	private Entry testEntry;
	private Link abderaLink;

	@Before
	public void setUp() {
		testEntry = loadTestEntry();
	}

	@Test
	public void testGetTitle() {
		assertEquals(
				"input deal",
				new AtomLinkHandler(testEntry
						.getLink("http://temenostech.temenos.com/rels/input"))
						.getTitle());
	}

	@Test
	public void testGetHref() {
		assertEquals(
				"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/errors",
				new AtomLinkHandler(testEntry
						.getLink("http://temenostech.temenos.com/rels/errors"))
						.getHref());
	}

	@Test
	public void testGetRel() {
		assertEquals(
				"http://temenostech.temenos.com/rels/input",
				new AtomLinkHandler(testEntry
						.getLink("http://temenostech.temenos.com/rels/input"))
						.getRel());
	}

	@Test
	public void testGetBaseUrl() {
		assertEquals(
				"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001/",
				new AtomLinkHandler(testEntry
						.getLink("http://temenostech.temenos.com/rels/hold"))
						.getBaseUri());
	}

	@Test
	public void testGetEmbeddedPayloadWithValidProperties() {
		AtomLinkHandler errorsLinkHandler = new AtomLinkHandler(
				testEntry.getLink("http://temenostech.temenos.com/rels/errors"));
		assertNotNull(errorsLinkHandler);
		// TODO below tests to be mioved to Entity handler test case
		Payload embeddedErrors = errorsLinkHandler.getEmbeddedPayload();
		assertNotNull(embeddedErrors);
		// with no index
		assertEquals("NO SPECIFIED VALUE",
				embeddedErrors.entity().get("Errors_ErrorsMvGroup/Code"));
		// with index 0
		assertEquals("NON_FATAL_ERROR",
				embeddedErrors.entity().get("Errors_ErrorsMvGroup(0)/Type"));
		// with index 1
		assertEquals("INPUT MISSING",
				embeddedErrors.entity().get("Errors_ErrorsMvGroup(1)/Text"));		
		// with index 2
		assertEquals("verCustomer_Input.verCustomer_Input_ShortNameMvGroup.ShortName:en:1",
				embeddedErrors.entity().get("Errors_ErrorsMvGroup(2)/Info"));
		// with last index i.e., 4
		assertEquals("verCustomer_Input.Gender:1:1",
				embeddedErrors.entity().get("Errors_ErrorsMvGroup(4)/Info"));
		// Invalid indexes
		assertEquals("",
				embeddedErrors.entity().get("Errors_ErrorsMvGroup(5)/Info"));
		assertEquals("",
				embeddedErrors.entity().get("Errors_ErrorsMvGroup(200)/Info"));
	}

	@Test
	public void testGetNoEmbeddedPayload() {
		assertNull(new AtomLinkHandler(
				testEntry.getLink("http://temenostech.temenos.com/rels/hold"))
				.getEmbeddedPayload());
	}

	private Entry loadTestEntry() {
		Entry entry = new Abdera()
				.getParser()
				.<Entry> parse(
						AtomLinkHandler.class
								.getResourceAsStream("/atom_entry_with_link_embedded.txt"))
				.getRoot();
		return entry;
	}

}
