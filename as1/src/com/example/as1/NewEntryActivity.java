/* NewEntryActivity.java
 * 
 * This class allows the user to add a log entry to the file battery_log.txt.
 * 
 * The user inputs the following parameters:
 * 	- The entry date, using a date selector dialog
 * 	- The starting battery, in %, to two decimal places
 * 	- The ending battery, in %, to two decimal places
 * 	- The number of seconds elapsed as an integer
 * 
 * The date picker dialog ensures users can only select valid dates.
 * It also sets the default date to the current date.
 * 
 * When the user clicks the button to add a new log entry, the code checks
 * to make sure that the starting battery is greater than the ending battery
 * and that both values are between 0.00 and 100.00. It also ensures the number
 * of seconds is not negative. If the user has made an input error and the
 * values are not valid, they will be shown a dialog corresponding to the error
 * they made, and then given a chance to correct the error.
 * 
 * Once the button to submit the log entry is clicked, the entry is saved into
 * battery_log.txt with each field (year, month, day, start, end, seconds) on a
 * separate line. Each entry is added at the bottom of the file, below all other
 * entries. Once an entry has been added, a dialog allows the user to return to
 * the main activity.	
 * 
 * Note: Date Picker code in this class was taken directly from
 * http://stackoverflow.com/questions/3299392/date-picker-in-android
 * URL retrieved on October 1, 2012.
 */

package com.example.as1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class NewEntryActivity extends Activity {

	// variables for tracking date
	private int mYear;
	private int mMonth;
	private int mDay;

	private TextView mDateDisplay;
	private Button mPickDate;
	
	// string used for printing debug text
	public static String debugString = new String();

	// dialog IDs
	static final int DATE_DIALOG_ID = 0; // allow user to select a date
	static final int START_ERR_DIALOG_ID = 1; // inform user of error in start battery %
	static final int END_ERR_DIALOG_ID = 2; // inform user of error in end battery %
	static final int TIME_ERR_DIALOG_ID = 3; // inform user of error in # of seconds
	static final int MISSING_FIELDS_DIALOG_ID = 4; // inform user that some fields are missing
	static final int ADD_SUCCESS_DIALOG_ID = 5; // indicate the new log entry was successfully added
	static final int WRITE_ERR_DIALOG_ID = 6; // indicate that there was a write error when adding event
	static final int DEBUG_DIALOG_ID = 7; // display debugString
	static final int BATTERY_ERR_DIALOG_ID = 8; // inform user that start must be > end
	
	// debug mode
	static final boolean DEBUG = false;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_new_entry);

	    mDateDisplay = (TextView) findViewById(R.id.showDate);        
	    mPickDate = (Button) findViewById(R.id.datePickerButton);

	    mPickDate.setOnClickListener(new View.OnClickListener() {
	        @SuppressWarnings("deprecation")
			public void onClick(View v) {
	            showDialog(DATE_DIALOG_ID);
	        }
	    });

	    // get the current date
	    final Calendar c = Calendar.getInstance();
	    mYear = c.get(Calendar.YEAR);
	    mMonth = c.get(Calendar.MONTH);
	    mDay = c.get(Calendar.DAY_OF_MONTH);

	    // display the current date
	    updateDisplay();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_entry, menu);
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
    
    
    // update the date displayed in the proper format
    private void updateDisplay() {
    	
    	// format in assignment specification is yyyy-mm-dd
    	// so we need to add a leading 0 to the month and day if they are less than 10.
    	String mLead = new String(); // leading zero for month, if applicable
    	String dLead = new String(); // leading zero for day, if applicable
    	
    	if (mMonth + 1 < 10)
    		mLead = "0";
    	
    	if (mDay < 10)
    		dLead = "0";
    	
        this.mDateDisplay.setText(
            new StringBuilder()
                    .append(mYear).append("-").append(mLead)
                    .append(mMonth + 1).append("-").append(dLead) // month is 0-based, so add 1
                    .append(mDay).append(" "));
    }
    
    
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
    	        public void onDateSet(DatePicker view, int year, 
    	                              int monthOfYear, int dayOfMonth) {
    	            mYear = year;
    	            mMonth = monthOfYear;
    	            mDay = dayOfMonth;
    	            updateDisplay();
    	        }
    };
    
    /**
     * Code in this method was taken on October 1, 2012 from:
     * http://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
     */
    @Override
    protected Dialog onCreateDialog(int id) {
       switch (id) 
       {
       		case DATE_DIALOG_ID:
       			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
       		case START_ERR_DIALOG_ID:
       			AlertDialog.Builder startBuilder = new AlertDialog.Builder(this);
       			startBuilder.setMessage(getString(R.string.dialog_start_err))
       			       .setCancelable(true)
       			       .setPositiveButton("Return", null);
       			AlertDialog startAlert = startBuilder.create();
       			return startAlert;
       		case END_ERR_DIALOG_ID:
       			AlertDialog.Builder endBuilder = new AlertDialog.Builder(this);
       			endBuilder.setMessage(getString(R.string.dialog_end_err))
       			       .setCancelable(true)
       			       .setPositiveButton("Return", null);
       			AlertDialog endAlert = endBuilder.create();
       			return endAlert;
       		case TIME_ERR_DIALOG_ID:
       			AlertDialog.Builder timeBuilder = new AlertDialog.Builder(this);
       			timeBuilder.setMessage(getString(R.string.dialog_time_err))
       			       .setCancelable(true)
       			       .setPositiveButton("Return", null);
       			AlertDialog timeAlert = timeBuilder.create();
       			return timeAlert;
       		case MISSING_FIELDS_DIALOG_ID:
       			AlertDialog.Builder missingBuilder = new AlertDialog.Builder(this);
       			missingBuilder.setMessage(getString(R.string.dialog_missing_fields))
       			       .setCancelable(true)
       			       .setPositiveButton("Return", null);
       			AlertDialog missingAlert = missingBuilder.create();
       			return missingAlert;
       		case ADD_SUCCESS_DIALOG_ID:
       			AlertDialog.Builder addSuccessBuilder= new AlertDialog.Builder(this);
       			addSuccessBuilder.setMessage(getString(R.string.dialog_add_success))
       			       .setCancelable(true)
       			       .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
       			           public void onClick(DialogInterface dialog, int id) {
       		                NewEntryActivity.this.finish();
       			           }
       			       });
       			AlertDialog addSuccessAlert = addSuccessBuilder.create();
       			return addSuccessAlert;
       		case WRITE_ERR_DIALOG_ID:
       			AlertDialog.Builder writeErrorBuilder= new AlertDialog.Builder(this);
       			writeErrorBuilder.setMessage(getString(R.string.dialog_write_err))
       			       .setCancelable(true)
       			       .setPositiveButton("Okay", null);
       			AlertDialog writeErrorAlert = writeErrorBuilder.create();
       			return writeErrorAlert;
       		case DEBUG_DIALOG_ID:
       			AlertDialog.Builder debugBuilder= new AlertDialog.Builder(this);
       			debugBuilder.setMessage(debugString)
       			       .setCancelable(true)
       			       .setPositiveButton("Return", null);
       			AlertDialog debugAlert = debugBuilder.create();
       			return debugAlert;
       		case BATTERY_ERR_DIALOG_ID:
       			AlertDialog.Builder batteryBuilder= new AlertDialog.Builder(this);
       			batteryBuilder.setMessage(getString(R.string.dialog_battery_err))
       			       .setCancelable(true)
       			       .setPositiveButton("Return", null);
       			AlertDialog batteryAlert = batteryBuilder.create();
       			return batteryAlert;
       }
       return null;
    }
    
    // add information in the form to the log
    @SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	public void submitEntry(View view)
    {
    	// capture description
    	EditText descText = (EditText) findViewById(R.id.description);
    	String description = descText.getText().toString();
    	
    	EditText startText = (EditText) findViewById(R.id.startPercentage);
    	String startBattery = startText.getText().toString();
    	
    	EditText endText = (EditText) findViewById(R.id.endPercentage);
    	String endBattery = endText.getText().toString();
    	
    	EditText timeElapsed = (EditText) findViewById(R.id.timeElapsed);
    	String seconds = timeElapsed.getText().toString();
    	
    	// if some fields are blank, inform user and then return
    	if (startBattery.matches("") || endBattery.matches("") || seconds.matches(""))
    	{
    		showDialog(MISSING_FIELDS_DIALOG_ID);
    	}
    	else // otherwise, moving right along
    	{
    		double start = Double.parseDouble(startBattery);
        	double end = Double.parseDouble(endBattery);
        	int numSeconds = Integer.parseInt(seconds);
        	
        	// ensure values are in range
        	if (start > 100 || start < 0)
        	{
        		showDialog(START_ERR_DIALOG_ID);
        	}
        	else if (end > 100 || end < 0)
        	{
        		showDialog(END_ERR_DIALOG_ID);
        	}
        	else if (numSeconds < 0)
        	{
        		showDialog(TIME_ERR_DIALOG_ID);
        	}
        	else if (end > start)
        	{
        		showDialog(BATTERY_ERR_DIALOG_ID);
        	}
        	else
        	{
        		// File IO code from http://www.anddev.org/working_with_files-t115.html
        		
				/* Construct string to output to log file.
				 * The string will begin with the existing file contents
				 * because the log should have recent entries at the bottom. */
				String output = new String();
        		output += Integer.toString(mYear);
        		output += "\n";
        		output += Integer.toString(mMonth);
        		output += "\n";
        		output += Integer.toString(mDay);
        		output += "\n";
        		// if no description was entered, say that in the output
        		if (description.matches(""))
        		{
        			output += "<no description>";
        		}
        		else
        		{
        			output += description;
        		}
        		output += "\n";
        		output += startBattery;
        		output += "\n";
        		output += endBattery;
        		output += "\n";
        		output += seconds;
        		output += "\n";
        		
        		// check if the battery log file exists
        		File logFile = getBaseContext().getFileStreamPath("battery_log.txt");
        		
        		// create the file if it doesn't exist
        		if (!logFile.exists())
        		{
        			// open file output stream
        			FileOutputStream logOut;
					try {
						logOut = openFileOutput("battery_log.txt", MODE_WORLD_READABLE);
						OutputStreamWriter osw = new OutputStreamWriter(logOut);	

	        			// write blank string to file
	        			osw.write("");
	        			
	        			// ensure everything is written
	        			osw.flush();
	        			
	        			// close the file
	        			osw.close();
	        			
					} catch (IOException e) {
						e.printStackTrace();
					}	
        		}
        		
        		
				// now we're ready to output to the file
                try 
                {	
                	// open the file input stream
                	// code from http://stackoverflow.com/questions/5771366/reading-a-simple-text-file
                	InputStream inputStream = openFileInput("battery_log.txt");
                	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                	
                	// read the contents of the file
        			int i = inputStream.read();
        			while (i != -1)
        			{
        				byteArrayOutputStream.write(i);
        				i = inputStream.read();
        			}
        			inputStream.close();
        			
        			// append the new log entry to the existing contents
        			String temp = new String(byteArrayOutputStream.toString());
        			temp += output;
        			output = new String(temp);
        			
                	// open file output stream
        			FileOutputStream fileout = openFileOutput("battery_log.txt", MODE_WORLD_READABLE);
        			OutputStreamWriter osw = new OutputStreamWriter(fileout);	

        			// write output string to file
        			osw.write(output);
        			
        			// ensure everything is written
        			osw.flush();
        			
        			// close the file
        			osw.close();
        			
        			// open file input stream
        			FileInputStream fIn = openFileInput("battery_log.txt");
        			InputStreamReader isr = new InputStreamReader(fIn);
        			
        			// prepare a character array to read the file back in
        			char[] inputBuffer = new char[output.length()];
        			
        			// read the data into the buffer
        			isr.read(inputBuffer);
        			
        			// convert the characters into a string
        			String readString = new String(inputBuffer);
        			
        			// make sure what was read is the same as what was written
        			boolean isTheSame = output.equals(readString);

        			// log success or failure
        			Log.i("Read/Write to battery log", "success = " + isTheSame);
        			
        			if (isTheSame)
        			{
        				showDialog(ADD_SUCCESS_DIALOG_ID);
        			}
        			else
        			{
        				showDialog(WRITE_ERR_DIALOG_ID);
        			}
        		} 
                catch (IOException ioe) 
        		{
        			ioe.printStackTrace();
        			showDialog(WRITE_ERR_DIALOG_ID);
        		}
        	}
    	}
    }
    	    
}
