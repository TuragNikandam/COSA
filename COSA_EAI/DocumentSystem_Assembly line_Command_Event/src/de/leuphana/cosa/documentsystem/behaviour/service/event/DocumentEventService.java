package de.leuphana.cosa.documentsystem.behaviour.service.event;

public interface DocumentEventService {
	void addDocumentEventListener(DocumentEventListener documentEventListener);
	void removeDocumentEventListener(DocumentEventListener documentEventListener);
}