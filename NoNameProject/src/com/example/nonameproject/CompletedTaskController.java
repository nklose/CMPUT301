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
	public void addTask(Context context, Task newTask) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.example.nonameproject.TaskController#deleteTask(com.example.nonameproject.Task)
	 */
	public void deleteTask(Context context, Task task) {
		// TODO Auto-generated method stub

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
