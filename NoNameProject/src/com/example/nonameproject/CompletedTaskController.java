package com.example.nonameproject;

import java.util.ArrayList;

import android.content.Context;

/**
 * Controller responsible for modifying and reading completed tasks used by the application
 */
public class CompletedTaskController extends TaskController{

	/* (non-Javadoc)
	 * @see com.example.nonameproject.TaskController#addTask(com.example.nonameproject.Task)
	 */
	
	public CompletedTaskController(Context context){
		super();
		LocalTaskController local = NoNameApp.getLocalTaskController();
		SharedTaskController shared = NoNameApp.getSharedTaskController(context);
		if(local.tasks.size() == 0){
			local.readLocalTaskFile(context);
		}
		if(shared.tasks.size() == 0){
			shared.readSharedTaskFile(context);
		}
	}
	
	public void addTask(Context context, Task newTask) {
		tasks.add(newTask);
	}
	
	public void addTask(Task newTask) {
		tasks.add(newTask);
	}

	/* (non-Javadoc)
	 * @see com.example.nonameproject.TaskController#deleteTask(com.example.nonameproject.Task)
	 */
	public void deleteTask(Context context, Task task) {
		tasks.remove(task);
	}
	
	public void deleteTask(Task task) {
		tasks.remove(task);
	}
	
	public void deleteTask(Context context, int index) {
		tasks.remove(index);
	}
	
	public void deleteTask(int index) {
		tasks.remove(index);
	}
}
