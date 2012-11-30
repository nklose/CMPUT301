package com.example.nonameproject;

import java.io.IOException;
import java.util.ArrayList;

import network.AddSharedTaskThread;
import network.DeleteSharedTaskThread;
import network.GetSharedTasksThread;

import android.content.Context;

public class SharedTaskController extends TaskController{


	/**
	 * 
	 */
	public SharedTaskController() {
		super();
	}

	public void addAllTasks(ArrayList<Task> tasks){
		this.tasks = tasks;
	}
	
	public void addTask(Context context, Task newTask) {
		new AddSharedTaskThread().execute(newTask);
	}

	public void deleteTask(Context context, Task task) {
		new DeleteSharedTaskThread().execute(task);
	}

	/**
	 * @param fis
	 */
	public void readSharedTaskFile(){
		new GetSharedTasksThread().execute(this);
	}
	
}
