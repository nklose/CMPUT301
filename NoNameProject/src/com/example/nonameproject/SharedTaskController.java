package com.example.nonameproject;

import java.util.ArrayList;

import android.content.Context;

import com.example.nonameproject.Activities.ViewSharedTasksActivity;
import com.example.nonameproject.network.AddSharedTaskThread;
import com.example.nonameproject.network.DeleteSharedTaskThread;
import com.example.nonameproject.network.GetSharedTasksThread;

/**
 * Controller responsible for modifying and reading shared tasks used by the application.
 */
public class SharedTaskController extends TaskController{

	public SharedTaskController(Context context) {
		super();
		if( this.tasks.size() == 0){
			readSharedTaskFile(context);
		}
	}

	/**
	 * Sets the list of tasks to the given arraylist.
	 * @param tasks ArrayList of Task objects.
	 */
	public void addAllTasks(ArrayList<Task> tasks){
		this.tasks = tasks;
	}
	
	/**
	 * Adds a task to the list of tasks.
	 * @param context Current context.
	 * @param newTask Task object to be added.
	 */
	public void addTask(Context context, Task newTask) {
		tasks.add(newTask);
		new AddSharedTaskThread().execute(newTask);
	}

	/**
	 * Removes a given task from the list of tasks.
	 * @param context Current context.
	 * @param task Task to be removed.
	 */
	public void deleteTask(Context context, Task task) {
		tasks.remove(task);
		new DeleteSharedTaskThread().execute(task);
	}

	/**
	 * Reads the file containing the list of shared tasks.
	 * @param context Current context.
	 */
	public void readSharedTaskFile(Context context){
		if( context instanceof ViewSharedTasksActivity){
			((ViewSharedTasksActivity) context).setProgressBarIndeterminateVisibility(true);
		}
		new GetSharedTasksThread().execute(this, context);
	}
	
	/**
	 * Deletes the shared tasks both in memory and in the CrowdSourcer.
	 * Used in the tear-down of tests.
	 * @param context Current context. 
	 */
	public void nuke(){
		tasks = new ArrayList<Task>();
		SharedTaskIOAdapter.nuke();
	}
}
