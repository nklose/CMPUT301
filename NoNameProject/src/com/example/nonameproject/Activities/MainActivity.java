package com.example.nonameproject.Activities;

import com.example.nonameproject.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * Starts AddTaskActivity, which allows the user to create a new task. 
     * @param view
     */
    public void addTask(View view)
    {
    	Intent intent = new Intent(this, AddTaskActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Starts the activity which allows the user to view locally saved tasks.
     * @param view
     */
    public void viewLocalTasks(View view)
    {
    	Intent intent = new Intent(this, ViewLocalTasksActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Starts the activity which allows the user to view tasks shared online.
     * @param view
     */
    public void viewSharedTasks(View view)
    {
    	Intent intent = new Intent(this, ViewSharedTasksActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Starts the activity which allows the user to view tasks which have been
     * fulfilled by other users.
     * @param view
     */
    public void viewCompletedTasks(View view)
    {
    	Intent intent = new Intent(this, ViewCompletedTasksActivity.class);
    	startActivity(intent);
    }
}
