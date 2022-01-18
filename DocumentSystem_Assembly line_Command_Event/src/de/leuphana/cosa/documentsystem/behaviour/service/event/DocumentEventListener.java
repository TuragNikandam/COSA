package de.leuphana.cosa.documentsystem.behaviour.service.event;

import java.util.EventListener;

public interface DocumentEventListener extends EventListener {
	
	void onDocumentCreated(DocumentEvent documentEvent);
	void onDocumentRemoved(DocumentEvent documentEvent);
	void onDocumentEdited(DocumentEvent documentEvent);
}
