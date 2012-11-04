package com.example.nonameproject;

import java.util.ArrayList;

public abstract class TaskController {
	protected ArrayList<Task> tasks;
	
	public TaskController(){
		tasks = new ArrayList<Task>();
	}
	
	public Task getTask(int index){
		return tasks.get(index);
	}
	
	abstract void addTask(Task newTask);
	
	abstract void deleteTask(Task task);
}
