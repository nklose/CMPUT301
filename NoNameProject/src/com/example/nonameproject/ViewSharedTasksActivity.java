package com.example.nonameproject;

import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ViewSharedTasksActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_tasks_list);

		final ListView listViewLog = (ListView) findViewById(R.id.localTasksListView);

		try{
			final SharedTaskBaseAdapter adapter = new SharedTaskBaseAdapter(this);
			listViewLog.setAdapter(adapter);
			listViewLog.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
					//Start the View Local Task Activity
					Intent intent = new Intent(ViewSharedTasksActivity.this, EditSharedTaskActivity.class);
					intent.putExtra("position", position);
					startActivity(intent);
				}  
			});
		}
		catch(Exception e){
			Toast.makeText(ViewSharedTasksActivity.this, "Error: "+ e.getClass().getName() + " " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		//Rest the ListView
		final ListView listViewLog = (ListView) findViewById(R.id.localTasksListView);
		listViewLog.setAdapter(null);
		listViewLog.invalidateViews();
		listViewLog.setAdapter(new SharedTaskBaseAdapter(ViewSharedTasksActivity.this));
	}
}
