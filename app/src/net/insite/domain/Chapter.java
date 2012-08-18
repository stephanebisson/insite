package net.insite.domain;

import java.io.Serializable;

public class Chapter implements Serializable 
{
	private static final long serialVersionUID = 4194017493903152747L;

	private String name;
	
	private String text;

	public Chapter(String name, String text) {
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
}
