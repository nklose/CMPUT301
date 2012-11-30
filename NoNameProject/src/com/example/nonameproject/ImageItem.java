package com.example.nonameproject;

import java.util.Calendar;

/**
 * Represents an image item added as a task item to a task
 */
public class ImageItem extends TaskItem{
	private String image;

	public ImageItem(Calendar submitDate, 
			String image){
		super(submitDate);
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
