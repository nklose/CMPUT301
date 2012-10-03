/**
 * Ideally, this activity should allow the user to view the log 
 * in chronological order and remove selected entries.
 * Unfortunately, I was not able to fully implement its design.
 * 
 * Instead, this activity allows the user to perform two actions:
 * clearing the log file entirely, or viewing the log file in its
 * raw form.
 */

package com.example.as1;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

@SuppressLint("NewApi")
public class ViewLogActivity extends Activity {
	
	static final int LOG_CLEARED_DIALOG_ID = 0; // confirm log cleared
	static final int SHOW_RAW_LOG_DIALOG_ID = 1; // show raw log file
	
	String input = new String(); // to contain contents of log

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_log, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * Code in this method was taken on October 1, 2012 from:
     * http://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
     */
    @Override
    protected Dialog onCreateDialog(int id) {
       switch (id) 
       {
       		
       		case LOG_CLEARED_DIALOG_ID:
       			AlertDialog.Builder startBuilder = new AlertDialog.Builder(this);
       			startBuilder.setMessage(getString(R.string.dialog_log_cleared))
       			       .setCancelable(true)
       			       .setPositiveButton("Return", null);
       			AlertDialog startAlert = startBuilder.create();
       			return startAlert;
       		case SHOW_RAW_LOG_DIALOG_ID:
       			AlertDialog.Builder logBuilder = new AlertDialog.Builder(this);
       			logBuilder.setMessage(input)
       			       .setCancelable(true)
       			       .setPositiveButton("Return", null);
       			AlertDialog logAlert = logBuilder.create();
       			return logAlert;
       }
       return null;
    }
    
    /**
     * This method clears the log file battery_log.txt.
     * @post log file blank
     * @param view
     */
    @SuppressWarnings("deprecation")
	@SuppressLint("WorldReadableFiles")
	public void clearLog(View view)
    {
    	// open file output stream
		FileOutputStream fileout;
		try {
			fileout = openFileOutput("battery_log.txt", MODE_WORLD_READABLE);
			OutputStreamWriter osw = new OutputStreamWriter(fileout);	

			// create a blank string
			String output = new String();
			
			// write output string to file
			osw.write(output);
			
			// ensure everything is written
			osw.flush();
			
			// close the file
			osw.close();
			
			showDialog(LOG_CLEARED_DIALOG_ID);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
    /**
     * Shows the raw log file in a dialog.
     * @param view
     */
    @SuppressWarnings("deprecation")
	public void showRawLog(View view)
    {
	    // file input code from http://stackoverflow.com/questions/2492076/android-reading-from-an-input-stream-efficiently
		try {
			// open up the input stream
	    	InputStream inputStream = openFileInput("battery_log.txt");
	    	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    	
	    	// read the entire file
			int i = inputStream.read();
			while (i != -1)
			{
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
			
			// save the entire file into a string
			input = new String(byteArrayOutputStream.toString());
			
			showDialog(SHOW_RAW_LOG_DIALOG_ID);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}   
}
