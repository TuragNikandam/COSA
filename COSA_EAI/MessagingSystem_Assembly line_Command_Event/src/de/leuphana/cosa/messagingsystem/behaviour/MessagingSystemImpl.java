package de.leuphana.cosa.messagingsystem.behaviour;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.DeliveryReportEvent;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.MessagingEventListener;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.MessagingEventService;
import de.leuphana.cosa.messagingsystem.structure.DeliveryReport;
import de.leuphana.cosa.messagingsystem.structure.Sendable;
import de.leuphana.cosa.messagingsystem.structure.message.Message;
import de.leuphana.cosa.messagingsystem.structure.messagingfactory.AbstractMessagingFactory;
import de.leuphana.cosa.messagingsystem.structure.messagingprotocol.MessagingProtocol;

public class MessagingSystemImpl implements MessagingCommandService, MessagingEventService {
	private Logger logger;
	private Set<MessagingEventListener> messagingEventListeners;

	public MessagingSystemImpl(String name) {
		messagingEventListeners = new HashSet<MessagingEventListener>();
	}

	@Override
	public DeliveryReport sendMessage(Sendable sendable) {
		logger = LogManager.getLogger(this.getClass());
		
		AbstractMessagingFactory abstractMessagingFactory = AbstractMessagingFactory.getFactory(sendable.getMessageType());

		Message message = abstractMessagingFactory.createMessage(sendable.getSender(), sendable.getReceiver(), sendable.getContent());

		MessagingProtocol messageProtocol = abstractMessagingFactory.createMessagingProtocol();
		messageProtocol.open();
		messageProtocol.transfer(message);
		messageProtocol.close();

		logger.info("Message: " + sendable.getContent() + "transported via " + sendable.getMessageType());
		
		DeliveryReport deliveryReport =  new DeliveryReport();
		deliveryReport.setSender(sendable.getSender());
		deliveryReport.setReceiver(sendable.getReceiver());
		deliveryReport.setContent(sendable.getContent());
		deliveryReport.setMessageType(sendable.getMessageType().toString());
		
		for (MessagingEventListener messagingEventListener : messagingEventListeners) {
			messagingEventListener.onMessageSent(new DeliveryReportEvent(deliveryReport));
		}

		return deliveryReport;
	}

	@Override
	public void addMessagingEventListener(MessagingEventListener messagingEventListener) {
		messagingEventListeners.add(messagingEventListener);
	}

	@Override
	public void removeMessagingEventListener(MessagingEventListener messagingEventListener) {
		messagingEventListeners.remove(messagingEventListener);
	}

}
