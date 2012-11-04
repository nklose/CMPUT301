package com.example.nonameproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class LocalTaskIOAdapter {
	//Location of the locally stored tasks
	public final static String localTaskFileName = "localTasks.log";

	/**
	 * @param fis
	 * @return
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

	public static void overwriteLocalLog(Context context, ArrayList<Task> tasks){
		try{
			FileOutputStream fos = context.openFileOutput(localTaskFileName, 
					context.MODE_PRIVATE);
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
	
	public static void appendToLocalLog(Context context, Task task){
		try{
			FileOutputStream fos = context.openFileOutput(localTaskFileName, 
					context.MODE_APPEND);
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
}
