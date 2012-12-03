package com.example.nonameproject;

import java.util.Calendar;

/**
 * Represents an image item added as a task item to a task
 */
public class ImageItem extends TaskItem{
	private static final long serialVersionUID = -972826834072674247L;
	private String image;
	private byte[] imageData;

	public ImageItem(Calendar submitDate, 
			String image, byte[] imageData){
		super(submitDate);
		this.image = image;
		this.imageData = imageData;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
}
