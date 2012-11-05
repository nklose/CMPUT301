package com.example.nonameproject;

import java.util.ArrayList;

import android.content.Context;

/**
 * Controller responsible for modifying and reading shared tasks used by the application
 */
public class SharedTaskController extends TaskController{


	/**
	 * 
	 */
	public SharedTaskController() {
		super();
	}

	
	public void addTask(Context context, Task newTask) {
		// TODO Auto-generated method stub
		
	}

	public void deleteTask(Context context, Task task) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param fis
	 */
	public void readLocalTaskFile(Context context){
		tasks = SharedIOAdapter.getSharedTasks(context);
	}
	
	public void updateTask(Context context, Task task){
		
	}
	
	/*
	 * Deletes the shared tasks both in memory and crowdsourcer
	 * Used in the teardown of tests
	 * 
	 * @param
	 * 	context - 
	 */
	public void nuke(){
		tasks = new ArrayList<Task>();
		SharedIOAdapter.nuke();
	}
}
