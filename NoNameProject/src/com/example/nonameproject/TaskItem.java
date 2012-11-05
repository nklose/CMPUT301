package com.example.nonameproject;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Abstract class that represents a task's task item
 */
public abstract class TaskItem{
	private Integer taskId;
	private Integer taskItemId;
	private Calendar submitDate;
	
	TaskItem(Integer taskId, Integer taskItemId, Calendar submitDate){
		this.taskId = taskId;
		this.taskItemId = taskItemId;
		this.submitDate = submitDate;
	}
	
	public Integer getTaskid() {
		return taskId;
	}
	public void setTaskid(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getTaskItemId() {
		return taskItemId;
	}
	public void setTaskItemId(Integer taskItemId) {
		this.taskItemId = taskItemId;
	}
	public Calendar getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Calendar submitDate) {
		this.submitDate = submitDate;
	}
}
