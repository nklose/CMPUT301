package com.example.nonameproject.Activities;

import java.util.Random;

import com.example.nonameproject.CompletedTaskController;
import com.example.nonameproject.ImageItemArrayAdapter;
import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;
import com.example.nonameproject.SharedTaskController;
import com.example.nonameproject.Task;
import com.example.nonameproject.TaskItem;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ViewACompletedTaskActivity extends Activity {
	private int position;	
	private CompletedTaskController controller = NoNameApp.getCompletedTaskController(this);
	private ArrayAdapter<TaskItem> adapter;
	private ListView listViewLog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_acompleted_task);
		Intent intent = getIntent(); 
		position = intent.getIntExtra("position", 0);
		Task task = controller.getTask(position);

		listViewLog = (ListView) findViewById(R.id.completedTasksListView);

		position = intent.getIntExtra("position", 0);
		task = controller.getTask(position);
		try {
			//Check if text task
			if(task.getType() == Task.TaskType.TASK_TEXT){
				adapter = new ArrayAdapter<TaskItem>(this, android.R.layout.simple_list_item_1, task.getTaskItems());
			}
			else{
				adapter = new ImageItemArrayAdapter(this, R.layout.custom_image_task_item_row, task.getTaskItems());
			}
		} catch (Exception e) {}
		listViewLog.setAdapter(adapter);
		
		TextView textView = (TextView) findViewById(R.id.completedTaskTitle);
		textView.setText(task.getTitle());

		textView = (TextView) findViewById(R.id.completedTaskDesc);
		textView.setText(task.getDescription());

		textView = (TextView) findViewById(R.id.completedTaskCreator);
		textView.setText(task.getCreator());

		Task.TaskType type = task.getType();	
		textView = (TextView) findViewById(R.id.completedTaskType);
		if (type == Task.TaskType.TASK_TEXT){
			textView.setText("Text");
		} else if (type == Task.TaskType.TASK_IMAGE){
			textView.setText("Image");
		} else if (type == Task.TaskType.TASK_AUDIO){
			textView.setText("Audio");
		}

		textView = (TextView) findViewById(R.id.completedTaskItemsRequested);
		String numItems = String.valueOf(task.getNumRequiredItems());
		textView.setText(numItems);
	}

}
