package com.example.nonameproject;

import java.text.SimpleDateFormat;
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
	
	public String toString(){
		String date = null;
		StringBuilder stringBuilder = new StringBuilder("Description:");
		stringBuilder.append(this.description);
		stringBuilder.append("\n Date: ");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		if (this.getSubmitDate() != null) {
			date = dateFormat.format(this.getSubmitDate().getTime());
		}
		
		stringBuilder.append(date);
		return stringBuilder.toString();
	}
}
