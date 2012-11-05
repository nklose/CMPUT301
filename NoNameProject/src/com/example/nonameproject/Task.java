package com.example.nonameproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Task implements Serializable{
	private String id;
	private String title;
	private String description;
	private String creator;
	private Integer numRequiredItems;
	private Boolean completed;
	private TaskType type;
	private Calendar submitDate;
	private ArrayList<TaskItem> taskItems;
	private static final long serialVersionUID = 0L;
	
	public enum TaskType {
		TASK_TEXT, TASK_IMAGE, TASK_AUDIO, TASK_INVALID;
	}
	
	//Constructor used when a new task is being created
	public Task(String title, String description, String creator,
			Integer numRequiredItems, TaskType type, 
			Calendar submitDate){
		this.id = "";
		this.title = title;
		this.description = description;
		this.creator = creator;
		this.numRequiredItems = numRequiredItems;
		this.completed = false;
		this.type = type;
		this.submitDate = submitDate;
		this.taskItems = new ArrayList<TaskItem>();
	}
	
	//Constructor used when creating a task from existing task data
	public Task(String id, String title, String description, String creator,
			Integer numRequiredItems, Boolean completed, TaskType type, 
			Calendar submitDate){
		this.id = id;
		this.title = title;
		this.description = description;
		this.creator = creator;
		this.numRequiredItems = numRequiredItems;
		this.completed = completed;
		this.type = type;
		this.submitDate = submitDate;
		this.taskItems = new ArrayList<TaskItem>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getNumRequiredItems() {
		return numRequiredItems;
	}

	public void setNumRequiredItems(Integer numRequiredItems) {
		this.numRequiredItems = numRequiredItems;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public Calendar getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Calendar submitDate) {
		this.submitDate = submitDate;
	}

	public ArrayList<TaskItem> getTaskItems() {
		return taskItems;
	}

	public void setTaskItems(ArrayList<TaskItem> taskItems) {
		this.taskItems = taskItems;
	}
	
	public void addTaskItem(TaskItem taskItem){
		taskItems.add(taskItem);
	}
}
