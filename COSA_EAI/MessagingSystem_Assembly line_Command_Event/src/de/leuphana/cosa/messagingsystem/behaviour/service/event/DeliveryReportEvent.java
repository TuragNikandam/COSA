package de.leuphana.cosa.messagingsystem.behaviour.service.event;

import java.util.EventObject;

import de.leuphana.cosa.messagingsystem.structure.DeliveryReport;

public class DeliveryReportEvent extends EventObject {
	private DeliveryReport deliveryReport;

	public DeliveryReportEvent(DeliveryReport deliveryReport) {
		super(deliveryReport);
		this.deliveryReport = deliveryReport;
	}
	
	public DeliveryReport getDeliveryReport() {
		return deliveryReport;
	}

}