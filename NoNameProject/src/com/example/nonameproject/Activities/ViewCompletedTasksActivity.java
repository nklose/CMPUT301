package com.example.nonameproject.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nonameproject.CompletedTaskBaseAdapter;
import com.example.nonameproject.CompletedTaskController;
import com.example.nonameproject.LocalTaskController;
import com.example.nonameproject.Task;
import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;



public class ViewCompletedTasksActivity extends Activity {
	private CompletedTaskBaseAdapter adapter = new CompletedTaskBaseAdapter(this);
	private static String deviceId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_completed_tasks);

		final ListView listViewLog = (ListView) findViewById(R.id.localTasksListView);

		try{
			deviceId = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);

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

			// listen for item long presses
			listViewLog.setOnItemLongClickListener(new OnItemLongClickListener() {

				public boolean onItemLongClick(AdapterView<?> a, View v, final int position, long id) 
				{	
					CompletedTaskController controller = NoNameApp.getCompletedTaskController(ViewCompletedTasksActivity.this);
					Task task = controller.getTask(position);
					if( deviceId.equals(task.getDeviceId())){
						// create a dialog to confirm deletion
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewCompletedTasksActivity.this);
						alertDialog.setTitle("Delete this task?");
						//alertDialog.setMessage("Are you sure you want delete this?");

						// yes button
						alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								which = position;

								// get controller 
								CompletedTaskController controller = NoNameApp.getCompletedTaskController(ViewCompletedTasksActivity.this);

								// delete the task
								controller.deleteTask(getBaseContext(), position);

								// restart the activity
								finish();
								startActivity(getIntent());
							}
						});

						alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() 
						{
							public void onClick(DialogInterface dialog, int which) 
							{
								dialog.cancel();
							}
						});

						// show delete confirmation dialog
						alertDialog.show();

						return true;
					}
					return true;
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
