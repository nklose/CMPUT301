package com.example.nonameproject.network;

import android.os.AsyncTask;

import com.example.nonameproject.SharedTaskIOAdapter;

public class NukeTasksThread extends AsyncTask<Void, Void, Void> {

	@Override
	protected Void doInBackground(Void... args) {
		try {
			SharedTaskIOAdapter.nuke();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
