package de.leuphana.cosa.documentsystem.structure;

public class Document {
	private String content;
	private String name;

	public Document(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public String getName() {
		return name;
	}

}