package com.example.nonameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;

public class LocalTaskController extends TaskController{

	public LocalTaskController() {
		super();
	}
	/**
	 * 
	 */
	public LocalTaskController(Context context) {
		super();
		readLocalTaskFile(context);
	}

	/* (non-Javadoc)
	 * @see com.example.nonameproject.TaskController#addTask(com.example.nonameproject.Task)
	 */
	public void addTask(Context context, Task newTask) {
		tasks.add(newTask);
		LocalTaskIOAdapter.appendToLocalLog(context, newTask);
		
	}

	/* (non-Javadoc)
	 * @see com.example.nonameproject.TaskController#deleteTask(com.example.nonameproject.Task)
	 */
	public void deleteTask(Context context, int index) {
		tasks.remove(index);
		LocalTaskIOAdapter.overwriteLocalLog(context, tasks);
	}
	
	public void deleteTask(Context context, Task task) {
		tasks.remove(task);
		LocalTaskIOAdapter.overwriteLocalLog(context, tasks);
	}


	/**
	 * @param fis
	 */
	public void readLocalTaskFile(Context context){
		tasks = LocalTaskIOAdapter.readLocalLog(context);
	}
}
