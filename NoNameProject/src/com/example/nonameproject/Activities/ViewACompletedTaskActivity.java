package com.example.nonameproject.Activities;

import java.util.Calendar;

import com.example.nonameproject.CompletedTaskController;
import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;
import com.example.nonameproject.SharedTaskController;
import com.example.nonameproject.Task;
import com.example.nonameproject.TextItem;
import com.example.nonameproject.R.layout;
import com.example.nonameproject.R.menu;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ViewACompletedTaskActivity extends Activity {
	private int position;	
	private CompletedTaskController controller = NoNameApp.getCompletedTaskController(this);
	private Dialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_acompleted_task);
		Intent intent = getIntent();
		String deviceId = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID); 
		position = intent.getIntExtra("position", 0);
		Task task = controller.getTask(position);

		TextView textView = (TextView) findViewById(R.id.taskTitle);
		textView.setText(task.getTitle());
		if( deviceId.equals(task.getDeviceId())){
			textView.setEnabled(true);
			textView.setFocusable(true);
		}
		textView = (TextView) findViewById(R.id.taskDescription);
		textView.setText(task.getDescription());
		if( deviceId.equals(task.getDeviceId())){
			textView.setEnabled(true);
			textView.setFocusable(true);
		}
		textView = (TextView) findViewById(R.id.taskCreator);
		textView.setText(task.getCreator());
		if( deviceId.equals(task.getDeviceId())){
			textView.setEnabled(true);
			textView.setFocusable(true);
		}

		Task.TaskType type = task.getType();	
		RadioButton textRB = (RadioButton) findViewById(R.id.addTaskTextRadio);
		RadioButton imageRB = (RadioButton) findViewById(R.id.addTaskImageRadio);
		RadioButton audioRB = (RadioButton) findViewById(R.id.addTaskAudioRadio);
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
