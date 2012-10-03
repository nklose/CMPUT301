/**
 * This activity is the main activity for the app. It shows the user
 * three buttons which can be used to navigate to the other activities:
 * View/Edit Log, New Log Entry, and Statistics.
 */

package com.example.as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.as1.MESSAGE";
		
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
     * Go to the log viewer and editor when the user clicks the view_log button.
     * @post begin ViewLogActivity
     * */
    public void viewLog(View view) {
    	Intent intent = new Intent(this, ViewLogActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Go to the activity which allows users to add new log entries.
     * @post begin NewEntryActivity
     */
    public void newEntry(View view) {
    	Intent intent = new Intent(this, NewEntryActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Go to the activity which allows users to view statistics about their log entries.
     * @post begin ViewStatsActivity
     */
    public void viewStats(View view) {
    	Intent intent = new Intent(this, ViewStatsActivity.class);
    	startActivity(intent);
    }
}
