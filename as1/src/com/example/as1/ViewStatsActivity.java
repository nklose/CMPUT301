/**
 * This activity allows the user to view their current battery statistics.
 * It reads the battery log file, then calculates the total battery used,
 * number of seconds logged, and the total battery used per second.
 * The values are then displayed to the user. Everything is calculated onResume()
 * so the values will update every time the user looks at the statistics page.
 * This activity is accessible via a button on the main activity.
 */

package com.example.as1;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewStatsActivity extends Activity {

    @SuppressLint({ "NewApi", "NewApi" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_stats, menu);
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
     * Whenever the view stats activity is resumed, the statistics should update
     * by pulling information from the battery log file and running calculations.
     */
	@Override
    public void onResume() 
    { 
        super.onResume();     
        
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
			String input = new String(byteArrayOutputStream.toString());
			
			// initialize the statistics
			double averageUsage = 0;
			int totalTime = 0;
			double totalBattery = 0;
			
			// iterate through the file input string
			for (int j = 0, line = 0; j < input.length(); j++)
			{
				// string containing current line
				String cur = new String();
				
				// if the current line mod 7 is 4, it means the current line indicates a starting battery entry
				if (line % 7 == 4)
				{
					// read starting battery entry until the end of the line
					while (input.charAt(j) != '\n')
					{
						// add each character in the line to the current line string
						cur += input.charAt(j); 
						j++;
					}
					
					// line ended, so move to the next one
					j++; line++;
					
					// save the line into an integer representing the starting battery life
					int startBattery = Integer.parseInt(cur);
					
					// clear the current line string
					cur = new String(); 
					
					// read the next line, which should contain ending battery life
					while (input.charAt(j) != '\n')
					{
						cur += input.charAt(j); 
						j++;
					}
					
					// move to the next line
					j++; line++;
					
					// save ending battery life into an integer
					int endBattery = Integer.parseInt(cur);
					cur = new String();
					
					// update total battery using the numbers read
					totalBattery += (startBattery - endBattery);
					
					// read the next line, which should contain seconds
					while (input.charAt(j) != '\n')
					{
						cur += input.charAt(j);
						j++;
					}
					
					// update total time with the number read
					totalTime += Integer.parseInt(cur);
				}
				
				// if at the end of a line, increment the line number
				if (input.charAt(j) == '\n')
					line++;
			}
			
			// calculate the average rounded to two decimal places
			averageUsage = roundTwoDecimals(totalBattery / totalTime);
			
			// update the interface with the statistics calculated
			TextView totalBatteryValue = (TextView) findViewById(R.id.batteryUsedValue);
			totalBatteryValue.setText(totalBattery + "%");
			TextView totalTimeValue = (TextView) findViewById(R.id.timeElapsedValue);
			totalTimeValue.setText(totalTime + " Seconds");
			TextView averageUsageValue = (TextView) findViewById(R.id.averageUsageValue);
			averageUsageValue.setText(averageUsage + "% per Second");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
    }
	
	/**
	 * Rounds a given double to two decimal places.
	 * Taken from http://www.java-forums.org/advanced-java/4130-rounding-double-two-decimal-places.html
	 * @param a double
	 * @return the new double, rounded to two decimal places
	 */
	private double roundTwoDecimals(double d) {
	        	DecimalFormat twoDForm = new DecimalFormat("#.##");
			return Double.valueOf(twoDForm.format(d));
	}
}
