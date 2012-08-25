package net.insite.domain;

import java.io.Serializable;

public class Chapter implements Serializable 
{
	private static final long serialVersionUID = 4194017493903152747L;

	private String name;
	
	private String text;
	
	private String id;

	public Chapter(String id, String name, String text) {
		this.id = id;
		this.name = name;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
