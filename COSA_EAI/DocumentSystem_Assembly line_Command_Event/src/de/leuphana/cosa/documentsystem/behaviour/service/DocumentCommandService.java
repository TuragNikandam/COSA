package de.leuphana.cosa.documentsystem.behaviour.service;

import de.leuphana.cosa.documentsystem.structure.Document;

public interface DocumentCommandService {

	Document createDocument(String documentName);

	Document getDocument(String documentName);

}