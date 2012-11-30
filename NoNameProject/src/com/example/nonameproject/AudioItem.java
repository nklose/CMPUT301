package com.example.nonameproject;

import java.util.Calendar;
/**
 * Represents an audio item added as a task item to a task
 */
public class AudioItem extends TaskItem{
	private String audio;

	public AudioItem(Calendar submitDate, 
			String audio){
		super(submitDate);
		this.audio = audio;
	}
	
	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}
}
