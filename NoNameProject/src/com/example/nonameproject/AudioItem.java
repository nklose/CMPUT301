package com.example.nonameproject;

import java.util.Calendar;
/**
 * Represents an audio item added as a task item to a task
 */
public class AudioItem extends TaskItem{
	private String audio;

	public AudioItem(Integer taskId, Integer taskItemId, Calendar submitDate, 
			String audio){
		super(taskId, taskItemId, submitDate);
		this.audio = audio;
	}
	
	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}
}
