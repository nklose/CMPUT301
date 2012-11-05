package com.example.nonameproject;

import java.util.Calendar;

/**
 * Represents an text item added as a task item to a task
 */
public class TextItem extends TaskItem{
	private String description;

	public TextItem(Calendar submitDate, 
			String description){
		super(submitDate);
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
