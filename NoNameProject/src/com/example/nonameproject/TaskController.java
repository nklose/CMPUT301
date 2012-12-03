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
	
	/**
	 * @param context Current context.
	 * @param task New task to replace previous task with.
	 * @param index Position index of task to replace.
	 */
	public void updateTask(Context context, Task task, int index){
		if(!task.getCompleted()){
			tasks.set(index, task);
		} else {
			tasks.remove(index);
		}
		LocalTaskIOAdapter.overwriteLocalLog(context, tasks);
	}
	
	abstract void addTask(Context context, Task newTask);
	
	abstract void deleteTask(Context context, Task task);
}
