package net.insite.domain;

import java.io.Serializable;
import java.util.List;

public class Tour implements Serializable 
{
	private static final long serialVersionUID = -5897503972576883258L;
	
	private String title;
	
	private String id;
	
	private List<Chapter> chapters;

	public Tour(String id, String title) {
		this.id = id;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
