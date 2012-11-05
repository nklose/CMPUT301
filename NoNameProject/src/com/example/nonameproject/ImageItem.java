package com.example.nonameproject;

import java.util.Calendar;

public class ImageItem extends TaskItem{
	private String image;

	public ImageItem(Integer taskId, Integer taskItemId, Calendar submitDate, 
			String image){
		super(taskId, taskItemId, submitDate);
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
