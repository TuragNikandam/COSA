package de.leuphana.swa.messagingsystem.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.leuphana.cosa.messagingsystem.behaviour.MessagingSystemImpl;
import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.structure.MessageType;
import de.leuphana.cosa.messagingsystem.structure.Sendable;

class MessagingServiceTest {

	private static MessagingCommandService messagingService;
	private static String receiverAddress;
	private static String senderAddress;
	private static Sendable sendable;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		messagingService = new MessagingSystemImpl("MessagingSystem");
		
		
		sendable = new Sendable() {
			
			@Override
			public String getSender() {
				return "rainer.zufall@web.de";
			}
			
			@Override
			public String getReceiver() {
				return "slotos@leuphana.de";
			}

			@Override
			public String getContent() {
				return "This is a content!";
			}
			
			@Override
			public MessageType getMessageType() {
				return MessageType.EMAIL;
			}
			
			
		};
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		messagingService = null;
	}

	@Test
	void canMessageBeSentViaEmail() {
		Assertions.assertNotNull(messagingService.sendMessage(sendable));
	}
	
}
