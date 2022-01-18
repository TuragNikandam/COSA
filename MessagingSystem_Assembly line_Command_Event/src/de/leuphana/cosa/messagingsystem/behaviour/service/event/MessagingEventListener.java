package de.leuphana.cosa.messagingsystem.behaviour.service.event;

import java.util.EventListener;

public interface MessagingEventListener extends EventListener {
	void onMessageSent(DeliveryReportEvent deliveryReportEvent);
}
