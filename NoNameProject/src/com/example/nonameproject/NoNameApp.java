package com.example.nonameproject;

import android.app.Application;

public class NoNameApp extends Application{
	//Singletons
	transient private static TaskController localTaskController;
	transient private static TaskController sharedTaskController;
	transient private static TaskController completedTaskController;
}
