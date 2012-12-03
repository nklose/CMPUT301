package com.example.nonameproject;

import java.util.ArrayList;

import android.content.Context;
/**
 * Controller responsible for modifying and reading local tasks.
 */
public class LocalTaskController extends TaskController{

	// create an empty task controller
	public LocalTaskController() {
		super();
	}
	
	// create an empty controller and read local tasks from file
	public LocalTaskController(Context context) {
		super();
		readLocalTaskFile(context);
	}

	/**
	 * Adds a task to main memory and appends it to the local task file.
	 * @param context Current context.
	 * @param newTask The task to be added.
	 */
	public void addTask(Context context, Task newTask) {
		tasks.add(newTask);
		LocalTaskIOAdapter.appendToLocalLog(context, newTask);
		
	}

	/**
	 * Deletes a specified task from main memory and the local task file.
	 * @param context Current context.
	 * @param index The position of the task to be deleted.
	 */
	public void deleteTask(Context context, int index) {
		tasks.remove(index);
		LocalTaskIOAdapter.overwriteLocalLog(context, tasks);
	}
	
	/**
	 * Deletes a specified task from main memory and the local task file
	 * @param context Current context.
	 * @param task The task to be deleted.
	 */
	public void deleteTask(Context context, Task task) {
		tasks.remove(task);
		LocalTaskIOAdapter.overwriteLocalLog(context, tasks);
	}


	/**
	 * Reads all local tasks from the file into main memory.
	 * @param context Current context.
	 */
	public void readLocalTaskFile(Context context){
		tasks = LocalTaskIOAdapter.readLocalLog(context);
	}
	
	
	
	/**
	 * Deletes the local tasks both in memory and in the log file.
	 * Used primarily in the tear-down of tests.
	 * @param context Current context.
	 */
	public void nuke(Context context){
		tasks = new ArrayList<Task>();
		LocalTaskIOAdapter.nuke(context);
	}
}
