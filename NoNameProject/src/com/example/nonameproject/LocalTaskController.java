package com.example.nonameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
/**
 * Controller responsible for modifying and reading local tasks used by the application
 */
public class LocalTaskController extends TaskController{

	/*
	 * Creates a new LocalTaskController with empty 'tasks'
	 */
	public LocalTaskController() {
		super();
	}
	
	/*
	 * Creates a new LocalTaskCOntroller and reads in local tasks from file to 'tasks'
	 * 
	 */
	public LocalTaskController(Context context) {
		super();
		readLocalTaskFile(context);
	}

	/*
	 * Adds an input task to 'tasks' in main memery as well as appending the new task to the local task file
	 * 
	 * @param
	 * 	context - 
	 * 	task - Task to be added into local tasks
	 */
	public void addTask(Context context, Task newTask) {
		tasks.add(newTask);
		LocalTaskIOAdapter.appendToLocalLog(context, newTask);
		
	}

	/* 
	 * Deletes a specified task from main memory and the local task file
	 * 
	 * @param
	 * 	context - 
	 * 	index - THe position of the task in 'tasks' to be deleted
	 */
	public void deleteTask(Context context, int index) {
		tasks.remove(index);
		LocalTaskIOAdapter.overwriteLocalLog(context, tasks);
	}
	
	/* 
	 * Deletes a specified task from main memory and the local task file
	 * 
	 * @param
	 * 	context - 
	 * 	task - The task to be deleted
	 */
	
	public void deleteTask(Context context, Task task) {
		tasks.remove(task);
		LocalTaskIOAdapter.overwriteLocalLog(context, tasks);
	}


	/*
	 * Reads all local tasks from file into main memory
	 * 
	 * @param 
	 * 	context - 
	 */
	public void readLocalTaskFile(Context context){
		tasks = LocalTaskIOAdapter.readLocalLog(context);
	}
	
	/*
	 * Deletes the local tasks both in memory and the log file
	 * Used in the teardown of tests
	 * 
	 * @param
	 * 	context - 
	 */
	public void nuke(Context context){
		tasks = new ArrayList<Task>();
		LocalTaskIOAdapter.nuke(context);
	}
}
