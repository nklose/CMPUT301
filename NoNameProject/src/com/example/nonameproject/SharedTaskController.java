package com.example.nonameproject;

import android.content.Context;

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
	
}
