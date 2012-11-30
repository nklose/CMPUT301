package com.example.nonameproject.network;

import android.os.AsyncTask;

import com.example.nonameproject.SharedTaskIOAdapter;
import com.example.nonameproject.Task;

public class AddSharedTaskThread extends AsyncTask<Task, Void, Void> {

	@Override
	protected Void doInBackground(Task... task) {
		
		try {
			if( task[0].getId().equals("")){
				SharedTaskIOAdapter.add(task[0]);
			} else {
				SharedTaskIOAdapter.update(task[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
