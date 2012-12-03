package com.example.nonameproject;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Abstract class that represents a task's task item
 */
public abstract class TaskItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Calendar submitDate;
	
	TaskItem(Calendar submitDate){
		this.submitDate = submitDate;
	}

	public Calendar getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Calendar submitDate) {
		this.submitDate = submitDate;
	}
	public String toString(){
		return "";
	}
}
