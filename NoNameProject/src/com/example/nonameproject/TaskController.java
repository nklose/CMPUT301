package com.example.nonameproject;

import java.util.ArrayList;

import android.content.Context;

/**
 * Abstract class used when modifying and reading tasks
 */
public abstract class TaskController {
	protected ArrayList<Task> tasks;
	
	public TaskController(){
		tasks = new ArrayList<Task>();
	}
	
	public Task getTask(int index){
		return tasks.get(index);
	}
	
	
	
	public int getNumberOfTasks(){
		return tasks.size();
	}
	
	abstract void addTask(Context context, Task newTask);
	
	abstract void deleteTask(Context context, Task task);
}
