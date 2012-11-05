package com.example.nonameproject;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddTaskActivity extends Activity {

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
     * Adds a task either locally or to the server online.
     */
    public void createTask(View view)
    {
    	// initalize the task variables
    	String title = new String();
    	String description = new String();
    	String creator = new String();
    	int type = 0;
    	
    	// get context and controller
    	Context context = this.getApplicationContext();
    	LocalTaskController controller = NoNameApp.getLocalTaskController();
    	
    	// get input from user
    	EditText titleText = (EditText) findViewById(R.id.taskTitle);
    	EditText descText = (EditText) findViewById(R.id.taskDescription);
    	EditText creatorText = (EditText) findViewById(R.id.taskCreator);
    	EditText reqItemsText = (EditText) findViewById(R.id.addTaskItemsRequested);
    	RadioButton typeTextRadio = (RadioButton) findViewById(R.id.addTaskTextRadio);
    	RadioButton typeImageRadio = (RadioButton) findViewById(R.id.addTaskImageRadio);
    	RadioButton typeAudioRadio = (RadioButton) findViewById(R.id.addTaskAudioRadio);
    	CheckBox shareCheckbox = (CheckBox) findViewById(R.id.shareOnlineCheckbox);
    	
    	// set type based on radio buttons
    	if (typeImageRadio.isChecked())
    	{
    		type = 1;
    	}
    	else if (typeAudioRadio.isChecked())
    	{
    		type = 2;
    	}
    		
    	
    	// assign inputs to variables
    	title = titleText.getText().toString();
    	description = descText.getText().toString();
    	creator = creatorText.getText().toString();
    	int numRequiredItems = 0;
    	Calendar submitDate = null;
    	Task task = new Task(title, description, creator, numRequiredItems,
    			type, submitDate);
    	
    	// save the task locally
    	controller.addTask(context, task);
    }
}
