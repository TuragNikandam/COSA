package de.leuphana.cosa.connector.behaviour;

import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.structure.MessageType;
import de.leuphana.cosa.messagingsystem.structure.Sendable;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintReportEvent;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintReportEventListener;
import de.leuphana.cosa.printingsystem.structure.PrintReport;

public class PrintReportToSendableAdapter implements PrintReportEventListener{
	private MessagingCommandService messagingCommandService;
	private PrintReport printReport;
	private Sendable sendable; 
	
	public PrintReportToSendableAdapter(MessagingCommandService messagingCommandService) {
		this.messagingCommandService = messagingCommandService;
	}
	
	private Sendable convert(PrintReport printReport) {
		
		Sendable sendable = new Sendable() {
			
			@Override
			public String getContent() {
				String newLine = "\n";
				
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(newLine) //
				.append("Name: ") //
				.append(printReport.getName()).append(newLine) //
				.append("Number of pages: ") //
				.append(printReport.getNumberOfPages()).append(newLine) //
				.append("Price per pages: ") //
				.append(printReport.getPricePerPage()).append(newLine) //
				.append("Total price: ")//
				.append(printReport.getTotalPrice()).append(newLine);
				return stringBuilder.toString();
			}
		};
		
		return sendable;
	}

	@Override
	public void onPrintablePrinted(PrintReportEvent printReportEvent) {
		printReport = printReportEvent.getPrintReport();
		sendable = convert(printReport);
		sendable.setSender("slotos@leuphana.de");
		sendable.setReceiver("drews@leuphana.de");
		sendable.setMessageType(MessageType.EMAIL);
		messagingCommandService.sendMessage(sendable);
	}

}