package com.example.nonameproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nonameproject.CompletedTaskBaseAdapter;
import com.example.nonameproject.R;

public class ViewCompletedTasksActivity extends Activity {
	private CompletedTaskBaseAdapter adapter = new CompletedTaskBaseAdapter(this);
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_completed_tasks);

		final ListView listViewLog = (ListView) findViewById(R.id.localTasksListView);

		try{
			adapter = new CompletedTaskBaseAdapter(this);
			listViewLog.setAdapter(adapter);
			listViewLog.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
					//Start the View Local Task Activity
					Intent intent = new Intent(ViewCompletedTasksActivity.this, ViewTaskSolutionActivity.class);
					intent.putExtra("position", position);
					startActivity(intent);
				}
			});
		}
		catch(Exception e){
			Toast.makeText(ViewCompletedTasksActivity.this, "Error: "+ e.getClass().getName() + " " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		//Rest the ListView
		adapter.notifyDataSetChanged();
	}
}
