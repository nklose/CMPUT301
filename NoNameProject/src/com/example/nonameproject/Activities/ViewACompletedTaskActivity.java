package com.example.nonameproject.Activities;

import com.example.nonameproject.CompletedTaskController;
import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;
import com.example.nonameproject.Task;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.TextView;

public class ViewACompletedTaskActivity extends Activity {
	private int position;	
	private CompletedTaskController controller = NoNameApp.getCompletedTaskController(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_acompleted_task);
		Intent intent = getIntent(); 
		position = intent.getIntExtra("position", 0);
		Task task = controller.getTask(position);

		TextView textView = (TextView) findViewById(R.id.taskTitle);
		textView.setText(task.getTitle());

		textView = (TextView) findViewById(R.id.taskDescription);
		textView.setText(task.getDescription());

		textView = (TextView) findViewById(R.id.taskCreator);
		textView.setText(task.getCreator());

		Task.TaskType type = task.getType();	
		RadioButton textRB = (RadioButton) findViewById(R.id.addTaskTextRadio);
		textRB.setFocusable(false);
		RadioButton imageRB = (RadioButton) findViewById(R.id.addTaskImageRadio);
		imageRB.setFocusable(false);
		RadioButton audioRB = (RadioButton) findViewById(R.id.addTaskAudioRadio);
		audioRB.setFocusable(false);
		if (type == Task.TaskType.TASK_TEXT){
			textRB.setChecked(true);
		} else if (type == Task.TaskType.TASK_IMAGE){
			imageRB.setChecked(true);
		} else if (type == Task.TaskType.TASK_AUDIO){
			audioRB.setChecked(true);
		}

		textView = (TextView) findViewById(R.id.itemsRequested);
		String numItems = String.valueOf(task.getNumRequiredItems());
		textView.setText(numItems);
	}
}
