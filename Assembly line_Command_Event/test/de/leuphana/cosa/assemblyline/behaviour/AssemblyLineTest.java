package de.leuphana.cosa.assemblyline.behaviour;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AssemblyLineTest {
	private static AssemblyLine assemblyLine;
	
	@BeforeAll
	public static void setup() {
		assemblyLine = new AssemblyLine();
	}

	@Test
	void isDocumentCreatedTest() {
		Assert.assertNotNull(assemblyLine.start("New Document"));
	}
	
}