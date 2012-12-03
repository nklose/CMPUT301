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
 * Adapter responsible for task input and output to and from the local storage file on the SD card.
 */
public class LocalTaskIOAdapter {
	//Location of the locally stored tasks
	public final static String localTaskFileName = "localTasks.log";

	/**
	 * Reads in local tasks from file and returns them.
	 * @param context Current context.
	 * @return ArrayList of Tasks that were stored in the local task file
	 */
	public static ArrayList<Task> readLocalLog(Context context){
		ArrayList<Task> localTasksFromFile = localTasksFromFile(context);
		ArrayList<Task> localTasks = new ArrayList<Task>();

		CompletedTaskController completedController = NoNameApp.getCompletedTaskController(context);

		for( int i=0; i < localTasksFromFile.size() - 1; i++){
			if( localTasksFromFile.get(i).getCompleted() == false){
				localTasks.add(localTasksFromFile.get(i));
			} else {
				completedController.addTask(localTasksFromFile.get(i));
			}
		}

		return localTasks;
	}

	/**
	 * Reads a list of local tasks from the file and returns them.
	 * @param context Current context.
	 * @return ArrayList of Tasks that were stored in the local task file
	 */
	private static ArrayList<Task> localTasksFromFile(Context context) {
		ArrayList<Task> localTasksFromFile = new ArrayList<Task>();
		try {
			FileInputStream fis = context.openFileInput(localTaskFileName);
			ObjectInputStream objectStream = new ObjectInputStream(fis);
			localTasksFromFile = (ArrayList<Task>) objectStream.readObject();
			objectStream.close();
		}
		catch (Exception e) {
			Log.e("NoNameProject", "Unable to load entries.", e);
		}
		return localTasksFromFile;
	}

	/**
	 * Overwrites the local task file with an input collection of tasks.
	 * @param context Current context. 
	 * @param tasks List of tasks to write into the local file.	
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

	/**
	 * Appends a task to the local task file.
	 * @param context Current context.
	 * @param task The Task to append to the local task file.
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

	/**
	 * Resets the local task file by removing all local task entries.
	 * @param context Current context.
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
