package com.example.nonameproject.Activities;

import java.util.Calendar;

import com.example.nonameproject.LocalTaskController;
import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;
import com.example.nonameproject.SharedTaskController;
import com.example.nonameproject.Task;
import com.example.nonameproject.Activities.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddTaskActivity extends Activity {

	// number of seconds to show toast messages
	private final int TOAST_LENGTH = 3;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_task, menu);
        return true;
    }
    
    /**
     * Cancels the add-task command and returns to the main activity
     * @param view
     */
    public void cancel(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Creates a task from scratch and either saves it locally or shares it online.
     */
    public void createTask(View view)
    {
    	// initalize the task variables
    	String title = new String();
    	String description = new String();
    	String creator = new String();
    	String deviceId = new String(); // unique device ID creating the task
    	Task.TaskType type = Task.TaskType.TASK_INVALID;
    	Boolean shareOnline;
    	int numRequiredItems = 1;
    	
    	// get context and controllers
    	Context context = this.getApplicationContext();
    	LocalTaskController localController = NoNameApp.getLocalTaskController();
    	SharedTaskController sharedController = NoNameApp.getSharedTaskController(this.getApplicationContext());
    	
    	// get input objects
    	EditText titleText = (EditText) findViewById(R.id.taskTitle);
    	EditText descText = (EditText) findViewById(R.id.taskDescription);
    	EditText creatorText = (EditText) findViewById(R.id.taskCreator);
    	EditText reqItemsText = (EditText) findViewById(R.id.addTaskItemsRequested);
    	RadioButton typeTextRadio = (RadioButton) findViewById(R.id.addTaskTextRadio);
    	RadioButton typeImageRadio = (RadioButton) findViewById(R.id.addTaskImageRadio);
    	RadioButton typeAudioRadio = (RadioButton) findViewById(R.id.addTaskAudioRadio);
    	CheckBox shareCheckbox = (CheckBox) findViewById(R.id.shareOnlineCheckbox);
    	
    	// get task type from radio buttons
    	if (typeTextRadio.isChecked())
    	{
    		type = Task.TaskType.TASK_TEXT;
    	}
    	else if (typeImageRadio.isChecked())
    	{
    		type = Task.TaskType.TASK_IMAGE;
    	}
    	else if (typeAudioRadio.isChecked())
    	{
    		type = Task.TaskType.TASK_AUDIO;
    	}
    	
    	// get preference to share online or save locally
    	shareOnline = shareCheckbox.isChecked();
    	
    	// get inputs from input objects
    	title = titleText.getText().toString();
    	description = descText.getText().toString();
    	creator = creatorText.getText().toString();
    	deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID); 
    	try
    	{
    		numRequiredItems = Integer.parseInt(reqItemsText.getText().toString());
    	}
    	catch (Exception e) { }
    	Calendar submitDate = Calendar.getInstance();
    	
    	// initialize toast
    	// toast code from http://developer.android.com/guide/topics/ui/notifiers/toasts.html
    	CharSequence toastText;
    	Toast toast = null;
    	
    	// check if inputs are valid, and toast if they are not
    	if (title.matches(""))
    	{
    		// display missing title error
    		toastText = "Task title is required.";
    		toast = Toast.makeText(context, toastText, TOAST_LENGTH);
    		toast.show();
    	}
    	else if (creator.matches(""))
    	{
    		// display missing creator error
    		toastText = "Creator is required.";
    		toast = Toast.makeText(context, toastText, TOAST_LENGTH);
    		toast.show();
    	}
    	else if (numRequiredItems < 1)
    	{
    		// display required items error
    		toastText = "Required items must be > 0.";
    		toast = Toast.makeText(context, toastText, TOAST_LENGTH);
    		toast.show();
    	}
    	else
    	{
    		// create the task and store it
	    	Task task = new Task(title, description, creator, numRequiredItems,
	    			type, submitDate, deviceId);
	    	
	    	// save the task locally or online depending on checkbox
	    	if (!shareOnline)
	    	{
	    		localController.addTask(context, task);
	    		toastText = "Task saved locally.";
	    		toast = Toast.makeText(context, toastText, TOAST_LENGTH);
	    		toast.show();
	    	}
	    	else
	    	{
	    		sharedController.addTask(context, task);
	    		toastText = "Task shared online.";
	    		toast = Toast.makeText(context, toastText, TOAST_LENGTH);
	    		toast.show();
	    	}
	    	
	    	// return to the main screen
	    	Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
    	}
    }
}
