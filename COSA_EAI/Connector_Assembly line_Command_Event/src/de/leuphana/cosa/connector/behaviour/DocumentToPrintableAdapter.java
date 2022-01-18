package de.leuphana.cosa.connector.behaviour;

import de.leuphana.cosa.documentsystem.behaviour.service.event.DocumentEvent;
import de.leuphana.cosa.documentsystem.behaviour.service.event.DocumentEventListener;
import de.leuphana.cosa.documentsystem.structure.Document;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintingCommandService;
import de.leuphana.cosa.printingsystem.structure.PrintOptions;
import de.leuphana.cosa.printingsystem.structure.Printable;
import de.leuphana.cosa.printingsystem.structure.UserAccount;

public class DocumentToPrintableAdapter implements DocumentEventListener {;
	private Document document;
	private Printable printable;
	private PrintingCommandService printingCommandService;
	
	public DocumentToPrintableAdapter(PrintingCommandService printingCommandService) {
		this.printingCommandService = printingCommandService;
	}
	
	public Printable convert(Document document) {

		// anonyme innere Klassen
		Printable printable = new Printable() {

			public String getTitle() {
				return document.getName();
			}

			public String getContent() {
				return document.getContent();
			}
		};

		return printable;
	}
	
	public Printable getPrintable() {
		return printable;
	}

	@Override
	public void onDocumentCreated(DocumentEvent documentEvent) {
		document = documentEvent.getDocument();
		// mapping
		printable = convert(document);
		// method call
		printingCommandService.printDocument(printable, new PrintOptions(), new UserAccount());
	}

	@Override
	public void onDocumentRemoved(DocumentEvent documentEvent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDocumentEdited(DocumentEvent documentEvent) {
		// TODO Auto-generated method stub
	}
}