package com.example.nonameproject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Represents an image item added as a task item to a task
 */
public class ImageItem extends TaskItem{
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
