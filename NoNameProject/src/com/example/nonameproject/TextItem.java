package com.example.nonameproject;

import java.util.Calendar;

public class TextItem extends TaskItem{
	private String description;

	public TextItem(Integer taskId, Integer taskItemId, Calendar submitDate, 
			String description){
		super(taskId, taskItemId, submitDate);
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		return this.description;
	}
}
