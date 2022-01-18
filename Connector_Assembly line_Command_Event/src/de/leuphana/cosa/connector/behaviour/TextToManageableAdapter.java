package de.leuphana.cosa.connector.behaviour;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.structure.Manageable;

public class TextToManageableAdapter {
	private Manageable manageable;

	public TextToManageableAdapter(String content, String documentName, DocumentCommandService documentService) {
		manageable = convert(content, documentName);
	}

	private Manageable convert(String name, String content) {
		
		Manageable manageable = new Manageable() {

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getContent() {
				return content;
			}
		};
		
		return manageable;
	}

}