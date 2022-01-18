package de.leuphana.cosa.documentsystem.behaviour;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.behaviour.service.event.DocumentEvent;
import de.leuphana.cosa.documentsystem.behaviour.service.event.DocumentEventListener;
import de.leuphana.cosa.documentsystem.behaviour.service.event.DocumentEventService;
import de.leuphana.cosa.documentsystem.structure.Document;

public class DocumentSystemImpl implements DocumentCommandService, DocumentEventService {
	private Map<String, Document> documentMap;
	private Set<DocumentEventListener> documentEventListeners;
	
	public DocumentSystemImpl(String name) {
		documentMap = new HashMap<String, Document>();
		documentEventListeners = new HashSet<DocumentEventListener>();
	}

	@Override
	public Document createDocument(String documentName) {
		Document document = new Document(documentName);
		
		System.out.println("Document created: " + documentName);
		
		documentMap.put(documentName, document);
		
		for (DocumentEventListener documentEventListener : documentEventListeners) {
			documentEventListener.onDocumentCreated(new DocumentEvent(document));
		}
		
		return document;
	}
	
	@Override
	public Document getDocument(String documentName) {
		return documentMap.get(documentName);
	}

	@Override
	public void addDocumentEventListener(DocumentEventListener documentEventListener) {
		documentEventListeners.add(documentEventListener);
	}

	@Override
	public void removeDocumentEventListener(DocumentEventListener documentEventListener) {
		documentEventListeners.remove(documentEventListener);
	}

}