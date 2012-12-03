package com.example.nonameproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Stores data used to represent a task, including any
 * task items which have been added to the task.
 */
public class Task implements Serializable{
	private static final long serialVersionUID = -583565739244804544L;
	private String id;
	private String title;
	private String description;
	private String creator;
	private Integer numRequiredItems;
	private Boolean completed;
	private TaskType type;
	private Calendar submitDate;
	private ArrayList<TaskItem> taskItems;
	private String deviceId;
	
	// task categories
	public enum TaskType {
		TASK_TEXT, TASK_IMAGE, TASK_AUDIO, TASK_INVALID;
	}
	
	/**
	 * Creates a task from scratch.
	 * @param title Title of the task. (required)
	 * @param description A description of the task. (optional)
	 * @param creator The user who created the task. (required)
	 * @param numRequiredItems The number of items requested. (optional; default=1)
	 * @param type The type of the task, text or image. (required)
	 * @param submitDate The date of submission. (generated)
	 * @param deviceId The ID of the device used to create the task. (generated)
	 */
	public Task(String title, String description, String creator,
			Integer numRequiredItems, TaskType type, 
			Calendar submitDate, String deviceId){
		this.id = "";
		this.title = title;
		this.description = description;
		this.creator = creator;
		this.numRequiredItems = numRequiredItems;
		this.completed = false;
		this.type = type;
		this.submitDate = submitDate;
		this.taskItems = new ArrayList<TaskItem>();
		this.deviceId = deviceId;
	}
	
	/**
	 * Creates a task from existing data.
	 * @param id The unique ID number of the task, used for position indexing.
	 * @param title The title of the task.
	 * @param description A description of the task.
	 * @param creator The user who created the task.
	 * @param numRequiredItems The number of items requested. (default=1)
	 * @param completed Whether or not the task has been completed.
	 * @param type The type of task, text or image.
	 * @param submitDate The date the task was submitted.
	 * @param deviceId The device ID of the device used to create the task.
	 */
	public Task(String id, String title, String description, String creator,
			Integer numRequiredItems, Boolean completed, TaskType type, 
			Calendar submitDate, String deviceId){
		this.id = id;
		this.title = title;
		this.description = description;
		this.creator = creator;
		this.numRequiredItems = numRequiredItems;
		this.completed = completed;
		this.type = type;
		this.submitDate = submitDate;
		this.taskItems = new ArrayList<TaskItem>();
		this.deviceId = deviceId;
	}
	
	/**
	 * Create an online task from existing data.
	 * @param title The title of the task.
	 * @param description A description of the task.
	 * @param creator The user who created the task.
	 * @param numRequiredItems The number of items requested. (default=1)
	 * @param completed Whether or not the task has been completed.
	 * @param type The type of task, text or image.
	 * @param submitDate The date the task was submitted.
	 * @param deviceId The device ID of the device used to create the task.
	 */
	public Task(String title, String description, String creator,
			Integer numRequiredItems, Boolean completed, TaskType type, 
			Calendar submitDate, String deviceId){
		this.id = "";
		this.title = title;
		this.description = description;
		this.creator = creator;
		this.numRequiredItems = numRequiredItems;
		this.completed = completed;
		this.type = type;
		this.submitDate = submitDate;
		this.taskItems = new ArrayList<TaskItem>();
		this.deviceId = deviceId;
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
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * Adds a task item to the list of task items.
	 * @param taskItem Item to be added.
	 */
	public void addTaskItem(TaskItem taskItem){
		taskItems.add(taskItem);
	}
}
