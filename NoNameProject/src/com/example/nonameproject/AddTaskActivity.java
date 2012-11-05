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
    	int type = -1;
    	Boolean shareOnline;
    	
    	// get context and controllers
    	Context context = this.getApplicationContext();
    	LocalTaskController localController = NoNameApp.getLocalTaskController();
    	SharedTaskController sharedController = NoNameApp.getSharedTaskController();
    	
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
    	if (typeTextRadio.isChecked())
    	{
    		type = 0;
    	}
    	else if (typeImageRadio.isChecked())
    	{
    		type = 1;
    	}
    	else if (typeAudioRadio.isChecked())
    	{
    		type = 2;
    	}
    	
    	shareOnline = shareCheckbox.isChecked();
    	
    	// assign inputs to variables
    	title = titleText.getText().toString();
    	description = descText.getText().toString();
    	creator = creatorText.getText().toString();
    	int numRequiredItems = Integer.parseInt(reqItemsText.getText().toString());
    	Calendar submitDate = null;
    	
    	// check if inputs are valid
    	if (title.matches(""))
    	{
    		// display null title error
    	}
    	else if (creator.matches(""))
    	{
    		// display null creator error
    	}
    	else if (numRequiredItems < 1)
    	{
    		// display required items error
    	}
    	else
    	{
    		// create the task and store it
	    	Task task = new Task(title, description, creator, numRequiredItems,
	    			type, submitDate);
	    	
	    	// save the task locally or online depending on checkbox
	    	if (!shareOnline)
	    	{
	    		localController.addTask(context, task);
	    	}
	    	else
	    	{
	    		sharedController.addTask(context, task);
	    	}
    	}
    }
}
