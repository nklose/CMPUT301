package com.example.nonameproject;

import android.app.Application;
import android.content.Context;

/**
 * Represents the state of the application with the data stored in its 3 singleton controllers
 * Each controller is related to the usage of a certain task type (shared, local, completed)
 */
public class NoNameApp extends Application{
	//Singletons
	transient private static LocalTaskController localTaskController;
	transient private static SharedTaskController sharedTaskController;
	transient private static CompletedTaskController completedTaskController;
	
	public static LocalTaskController getLocalTaskController() {
		if(localTaskController == null) 
			localTaskController = new LocalTaskController();
		return localTaskController;
	}
	/**
	 * @return
	 */
	public static LocalTaskController getLocalTaskController(Context context) {
		if(localTaskController == null) 
			localTaskController = new LocalTaskController(context);
		return localTaskController;
	}

	/**
	 * @return
	 */
	public static SharedTaskController getSharedTaskController(Context context) {
		if(sharedTaskController == null) 
			sharedTaskController = new SharedTaskController(context);
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
