package de.leuphana.cosa.messagingsystem.behaviour;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.leuphana.cosa.documentsystem.behaviour.DocumentSystemImpl;
import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;

class DocumentServiceTest {

	private DocumentCommandService documentService;
	private String documentName;

	@BeforeEach
	void setUp() throws Exception {
		documentService = new DocumentSystemImpl("DocumentSystem");
		documentName = "Neues Dokument";
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void canDocumentBeCreatedTest() {
		Assert.assertNotNull(documentService.createDocument(documentName));
	}
}