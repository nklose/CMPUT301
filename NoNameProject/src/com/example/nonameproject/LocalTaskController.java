package com.example.nonameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class LocalTaskController extends TaskController{


	/**
	 * 
	 */
	public LocalTaskController() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.example.nonameproject.TaskController#addTask(com.example.nonameproject.Task)
	 */
	public void addTask(Task newTask) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.example.nonameproject.TaskController#deleteTask(com.example.nonameproject.Task)
	 */
	public void deleteTask(Task task) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param fis
	 */
	public void readLocalTaskFile(FileInputStream fis){
		tasks = LocalTaskIOAdapter.readLocalLog(fis);
	}
}
