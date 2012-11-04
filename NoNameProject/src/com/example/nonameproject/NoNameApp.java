package com.example.nonameproject;

import android.app.Application;

public class NoNameApp extends Application{
	//Singletons
	transient private static LocalTaskController localTaskController;
	transient private static SharedTaskController sharedTaskController;
	transient private static CompletedTaskController completedTaskController;
	
	/**
	 * @return
	 */
	public static LocalTaskController getLocalTaskController() {
		if(localTaskController == null) 
			localTaskController = new LocalTaskController();
		return localTaskController;
	}

	/**
	 * @return
	 */
	public static SharedTaskController getSharedTaskController() {
		if(sharedTaskController == null) 
			sharedTaskController = new SharedTaskController();
		return sharedTaskController;
	}

	/**
	 * @return
	 */
	public static CompletedTaskController getCompletedTaskController() {
		if(completedTaskController == null) 
			completedTaskController = new CompletedTaskController();
		return completedTaskController;
	}
}
