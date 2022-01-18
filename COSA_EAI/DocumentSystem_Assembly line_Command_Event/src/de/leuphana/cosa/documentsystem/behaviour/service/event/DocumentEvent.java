package de.leuphana.cosa.documentsystem.behaviour.service.event;

import java.util.EventObject;

import de.leuphana.cosa.documentsystem.structure.Document;

public class DocumentEvent extends EventObject {
	private Document document;

	public DocumentEvent(Document document) {
		super(document);
		this.document = document;
	}
	
	public Document getDocument() {
		return document;
	}

}