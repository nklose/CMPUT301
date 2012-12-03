package com.example.nonameproject;

import android.content.Context;

/**
 * Controller responsible for modifying and reading completed tasks used by the application
 */
public class CompletedTaskController extends TaskController{

	public CompletedTaskController(Context context){
		super();
//		LocalTaskController local = NoNameApp.getLocalTaskController();
//		SharedTaskController shared = NoNameApp.getSharedTaskController(context);
//		if(local.tasks.size() == 0){
//			local.readLocalTaskFile(context);
//		}
//		if(shared.tasks.size() == 0){
//			shared.readSharedTaskFile(context);
//		}
	}
	
	/**
	 * Adds a given task to the list of completed tasks.
	 * @param context Current context.
	 * @param newTask Task to be added.
	 */
	public void addTask(Context context, Task newTask) {
		tasks.add(newTask);
	}
	
	/**
	 * Adds a given task to the list without use of context.
	 * @param newTask Task to be added.
	 */
	public void addTask(Task newTask) {
		tasks.add(newTask);
	}

	/**
	 * Removes a given task from the list of completed tasks.
	 * @param context Current context.
	 * @param task Task to be deleted.
	 */
	public void deleteTask(Context context, Task task) {
		tasks.remove(task);
	}
	
	/**
	 * Removes a given task from the list without use of context.
	 * @param task Task to be deleted.
	 */
	public void deleteTask(Task task) {
		tasks.remove(task);
	}
	
	/**
	 * Removes a task specified by the given index from the list
	 * of completed tasks.
	 * @param context Current context.
	 * @param index Position index of the task to be removed.
	 */
	public void deleteTask(Context context, int index) {
		tasks.remove(index);
	}
	
	/**
	 * Removes a task specified by the given index from the list
	 * of completed tasks without use of context.
	 * @param index Position index of task to be removed.
	 */
	public void deleteTask(int index) {
		tasks.remove(index);
	}
}
