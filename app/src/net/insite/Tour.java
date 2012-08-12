package net.insite;

import java.io.Serializable;

public class Tour implements Serializable 
{
	private static final long serialVersionUID = -5897503972576883258L;
	
	private String title;

	public Tour(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
