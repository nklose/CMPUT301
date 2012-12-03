package com.example.nonameproject.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nonameproject.LocalTaskBaseAdapter;
import com.example.nonameproject.LocalTaskController;
import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;

/**
 * Displays a list of locally saved tasks.
 * 
 * Accessible by pressing the "View Local Tasks" button on MainActivity.
 * 
 * Tasks are displayed textually, including their title, creator, type,
 * and creation date and time. Other attributes (such as task items) are
 * not displayed here.
 * 
 * Pressing on a task starts EditLocalTaskActivity.
 * 
 * Long pressing on a task allows the user to delete it.
 */
public class ViewLocalTasksActivity extends Activity {
	private LocalTaskBaseAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_tasks_list);

		final ListView listViewLog = (ListView) findViewById(R.id.localTasksListView);
		
		// get context
		final Context context = this.getApplicationContext();
		
		try {
			adapter = new LocalTaskBaseAdapter(this);
			listViewLog.setAdapter(adapter);
			
			// listen for item clicks
			listViewLog.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					
					// start Edit Local Task Activity
					Intent intent = new Intent(ViewLocalTasksActivity.this, EditLocalTaskActivity.class);
					intent.putExtra("position", position);
					startActivity(intent);
				}  
			});
			
			// listen for item long presses
			listViewLog.setOnItemLongClickListener(new OnItemLongClickListener() {
				
				public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {
					// get controller
					LocalTaskController localController = NoNameApp.getLocalTaskController();
					
					// TODO: add delete confirmation dialog
					
					// delete the task
					localController.deleteTask(context, position);
					
					// restart the activity
					finish();
					startActivity(getIntent());
					
					return true;
				}
			});
		}
		catch(Exception e){
			// display the exception as a toast
			Toast.makeText(ViewLocalTasksActivity.this, "Error: "+ e.getClass().getName() 
					+ " " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		//Rest the ListView
		adapter.notifyDataSetChanged();
	}
}
