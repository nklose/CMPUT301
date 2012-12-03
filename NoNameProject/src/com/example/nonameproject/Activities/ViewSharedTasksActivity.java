package com.example.nonameproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;
import com.example.nonameproject.SharedTaskBaseAdapter;
import com.example.nonameproject.SharedTaskController;

public class ViewSharedTasksActivity extends Activity {
	private SharedTaskBaseAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_shared_tasks);

		final ListView listViewLog = (ListView) findViewById(R.id.sharedTasksListView);

		try{
			this.setProgressBarIndeterminateVisibility(false);
			adapter = new SharedTaskBaseAdapter(this);
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
		adapter.notifyDataSetChanged();
	}
	
	public void refreshData(){
		adapter.notifyDataSetChanged();
	}
	
	public void onRefresh(View view){
		SharedTaskController sharedTaskController = NoNameApp.getSharedTaskController(this);
		sharedTaskController.readSharedTaskFile(this);
	}
}
