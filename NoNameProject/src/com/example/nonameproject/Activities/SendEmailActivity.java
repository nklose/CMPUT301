/**
 * This activity is adapted from code available at
 * http://www.mkyong.com/android/how-to-send-email-in-android/
 */
package com.example.nonameproject.Activities;

import com.example.nonameproject.R;
import com.example.nonameproject.R.id;
import com.example.nonameproject.R.layout;
import com.example.nonameproject.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendEmailActivity extends Activity {

	Button sendButton;
	EditText emailRecipient;
	EditText emailSubject;
	EditText emailBody;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        
        sendButton = (Button) findViewById(R.id.sendEmailButton);
        emailRecipient = (EditText) findViewById(R.id.recipientField);
        emailSubject = (EditText) findViewById(R.id.subjectField);
        emailBody = (EditText) findViewById(R.id.bodyField);
        
        sendButton.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v)
        	{
        		String recipient = emailRecipient.getText().toString();
        		String subject = emailSubject.getText().toString();
        		String message = emailBody.getText().toString();
	   
        		Intent email = new Intent(Intent.ACTION_SEND);
        		email.putExtra(Intent.EXTRA_EMAIL, new String[]{ recipient });
        		email.putExtra(Intent.EXTRA_SUBJECT, subject);
        		email.putExtra(Intent.EXTRA_TEXT, message);
	   
        		email.setType("message/rfc822");
	   
        		startActivity(Intent.createChooser(email, "Send email with:"));
  			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_send_email, menu);
        return true;
    }
}
