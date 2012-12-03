package com.example.nonameproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

/**
 * Adapter responsible for task input and output to and from the local storage file on the SD card
 */
public class LocalTaskIOAdapter {
	//Location of the locally stored tasks
	public final static String localTaskFileName = "localTasks.log";

	/*
	 * Reads in local tasks from file and returns them
	 * 
	 * @param
	 * 	context -
	 * @return
	 * 	localTasks - ArrayList of Tasks that were stored in the local task file
	 */
	public static ArrayList<Task> readLocalLog(Context context){
		ArrayList<Task> localTasks = new ArrayList<Task>();

		//Read all the entries from the log (if they exist)
		try{
			FileInputStream fis = context.openFileInput(localTaskFileName);
			ObjectInputStream objectStream = new ObjectInputStream(fis);
			try{
				localTasks = (ArrayList<Task>) objectStream.readObject();
			} finally {
				objectStream.close();
			}
		} catch(ClassNotFoundException e){
			Log.e("NoNameProject", "Unable to load entries.", e);

		} catch(IOException e){
			Log.e("NoNameProject", "Unable to load entries.", e);
		}

		return localTasks;
	}
	
	/*
	 * Overwrites the lcoal task file with an input collection of tasks
	 * 
	 * @param
	 * 	context - 
	 * 	tasks - THe tasks to overwrite the local task file with	
	 */
	public static void overwriteLocalLog(Context context, ArrayList<Task> tasks){
		try{
			FileOutputStream fos = context.openFileOutput(localTaskFileName, 
					Context.MODE_PRIVATE);
			ObjectOutputStream objectStream = new ObjectOutputStream(fos);
			try{
				objectStream.writeObject(tasks);
			} finally {
				objectStream.close();
			}
		} catch(IOException e){
			Log.e("NoNameProject", "Unable to save entries.", e);
		}
	}

	/*
	 * Appends a task to the local task file
	 * 
	 * @param
	 * 	context
	 * 	task - The Task to append to the local task file
	 */
	public static void appendToLocalLog(Context context, Task task){
		try{
			FileOutputStream fos = context.openFileOutput(localTaskFileName, 
					Context.MODE_APPEND);
			ObjectOutputStream objectStream = new ObjectOutputStream(fos);
			try{
				objectStream.writeObject(task);
			} finally {
				objectStream.close();
			}
		} catch(IOException e){
			Log.e("NoNameProject", "Unable to save entry.", e);
		}
	}

	/*
	 * Resets the local task file by removing all local task entries
	 */
	public static void nuke(Context context){
		try{
			FileOutputStream fos = context.openFileOutput(localTaskFileName, 
					Context.MODE_PRIVATE);
			ObjectOutputStream objectStream = new ObjectOutputStream(fos);
			objectStream.close();
		} catch(IOException e){
			Log.e("NoNameProject", "Unable to save entries.", e);
		}
	}
}
