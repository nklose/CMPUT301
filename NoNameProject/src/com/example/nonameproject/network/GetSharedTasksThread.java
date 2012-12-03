package com.example.nonameproject.network;

import java.util.ArrayList;

import com.example.nonameproject.SharedTaskIOAdapter;
import com.example.nonameproject.SharedTaskController;
import com.example.nonameproject.Task;
import com.example.nonameproject.Activities.ViewSharedTasksActivity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class GetSharedTasksThread extends AsyncTask<Object, Void, Context> {

	@Override
	protected Context doInBackground(Object... args) {
		try {
			ArrayList<Task> tasks = new ArrayList<Task>();
			((Activity) args[1]).setProgressBarIndeterminateVisibility(true);
			tasks = SharedTaskIOAdapter.getSharedTasks();
			((SharedTaskController) args[0]).addAllTasks(tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ((Context)args[1]);
		
	}
	
	protected void onPostExecute(Context context){
		Toast.makeText(context, "Sync complete", Toast.LENGTH_LONG).show();
		((Activity) context).setProgressBarIndeterminateVisibility(false);
		if( context instanceof ViewSharedTasksActivity){
			((ViewSharedTasksActivity) context).refreshData();
		}
	}

}
