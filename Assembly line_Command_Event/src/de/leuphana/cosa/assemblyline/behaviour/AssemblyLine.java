package de.leuphana.cosa.assemblyline.behaviour;

import de.leuphana.cosa.connector.behaviour.DocumentToPrintableAdapter;
import de.leuphana.cosa.connector.behaviour.PrintReportToSendableAdapter;
import de.leuphana.cosa.connector.behaviour.TextToManageableAdapter;
import de.leuphana.cosa.documentsystem.behaviour.DocumentSystemImpl;
import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.behaviour.service.event.DocumentEventService;
import de.leuphana.cosa.messagingsystem.behaviour.MessagingSystemImpl;
import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.DeliveryReportEvent;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.MessagingEventListener;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.MessagingEventService;
import de.leuphana.cosa.messagingsystem.structure.DeliveryReport;
import de.leuphana.cosa.printingsystem.behaviour.PrintingSystemImpl;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintingCommandService;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintingEventService;

public class AssemblyLine {
	private static DeliveryReport deliveryReport;
	
	// Filter
	private DocumentSystemImpl documentSystemImpl;
	private PrintingSystemImpl printingSystemImpl;
	private MessagingSystemImpl messagingSystemImpl;
	
	// Pipes
	private TextToManageableAdapter textToManageableAdapter;
	private DocumentToPrintableAdapter documentToPrintableAdapter;
	private PrintReportToSendableAdapter printReportToSendableAdapter;

	public AssemblyLine() {
		// create filters
		documentSystemImpl = new DocumentSystemImpl("DocumentSystem");
		printingSystemImpl = new PrintingSystemImpl("PrintingSystem");
		messagingSystemImpl = new MessagingSystemImpl("MessagingSystem");

		// create pipes
//		textToManageableAdapter = new TextToManageableAdapter(null, null, (DocumentCommandService) documentSystemImpl);
		documentToPrintableAdapter = new DocumentToPrintableAdapter((PrintingCommandService) printingSystemImpl);
		printReportToSendableAdapter = new PrintReportToSendableAdapter((MessagingCommandService) messagingSystemImpl);
		
		// configure assembly line
		((DocumentEventService) documentSystemImpl).addDocumentEventListener(documentToPrintableAdapter);
		((PrintingEventService) printingSystemImpl).addPrintingEventListener(printReportToSendableAdapter);
	}

	public DeliveryReport start(String documentName) {
		// method call
		
		((MessagingEventService) messagingSystemImpl).addMessagingEventListener(new MessagingEventListener() {
			
			@Override
			public void onMessageSent(DeliveryReportEvent deliveryReportEvent) {
				deliveryReport = deliveryReportEvent.getDeliveryReport();
			}

		});

		((DocumentCommandService) documentSystemImpl).createDocument(documentName);
		
		return deliveryReport;
	}

}