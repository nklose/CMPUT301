package com.example.nonameproject.Activities;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nonameproject.ImageItem;
import com.example.nonameproject.LocalTaskController;
import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;
import com.example.nonameproject.SharedTaskController;
import com.example.nonameproject.Task;
import com.example.nonameproject.TextItem;

public class EditSharedTaskActivity extends Activity {

	private int position;	
	private SharedTaskController controller = NoNameApp.getSharedTaskController(this);
	private Dialog dialog;
	private Task task;
	private static final int TAKE_IMAGE_CODE = 111;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_shared_task);
		Intent intent = getIntent();
		String deviceId = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID); 
		position = intent.getIntExtra("position", 0);
		task = controller.getTask(position);
		
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
		if( deviceId.equals(task.getDeviceId())){
			textRB.setEnabled(true);
			textRB.setFocusable(true);
			imageRB.setEnabled(true);
			imageRB.setFocusable(true);
			audioRB.setEnabled(true);
			audioRB.setFocusable(true);
		}

		textView = (TextView) findViewById(R.id.itemsRequested);
		if( deviceId.equals(task.getDeviceId())){
			textView.setEnabled(true);
			textView.setFocusable(true);
		}
		String numItems = String.valueOf(task.getNumRequiredItems());
		textView.setText(numItems);
	}

	public void addTaskItem(View view){
		Task task = controller.getTask(position);
		if (task.getType() == Task.TaskType.TASK_TEXT){
			dialog = new Dialog(EditSharedTaskActivity.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.dialog_add_text_item);

			Button btnSave = (Button) dialog.findViewById(R.id.btnAddTextItem);
			btnSave.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View arg0)
	        	{
	    			EditText txtDescription = (EditText) dialog.findViewById(R.id.txtDescription);
	    			TextItem textItem = new TextItem(Calendar.getInstance(), txtDescription.getText().toString());
	    			Task task = controller.getTask(position);
	    			task.addTaskItem(textItem);
	    			dialog.dismiss();
	        	}
	        });
			
			Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
			btnCancel.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View arg0)
	        	{
	        		dialog.cancel();
	        	}
	        });
			dialog.show();
		} else if(task.getType() == Task.TaskType.TASK_IMAGE){
			//Start the take image activity
			Intent intent = new Intent(this, TakeImageActivity.class);
			startActivityForResult(intent, TAKE_IMAGE_CODE);
		}
	}

	public void saveTask(View view){
		
		Task oldTask = controller.getTask(position);

		// initalize the task variables
		String title = new String();
		String description = new String();
		String creator = new String();
		String deviceId = oldTask.getDeviceId();
		Task.TaskType type = Task.TaskType.TASK_INVALID;
		Boolean completed = oldTask.getCompleted();
		int numRequiredItems = oldTask.getNumRequiredItems();

		// get context and controllers
		Context context = this.getApplicationContext();
		SharedTaskController sharedController = NoNameApp.getSharedTaskController(context);

		// get input from user
		EditText titleText = (EditText) findViewById(R.id.taskTitle);
		EditText descText = (EditText) findViewById(R.id.taskDescription);
		EditText creatorText = (EditText) findViewById(R.id.taskCreator);
		EditText reqItemsText = (EditText) findViewById(R.id.itemsRequested);
		RadioButton typeTextRadio = (RadioButton) findViewById(R.id.addTaskTextRadio);
		RadioButton typeImageRadio = (RadioButton) findViewById(R.id.addTaskImageRadio);
		RadioButton typeAudioRadio = (RadioButton) findViewById(R.id.addTaskAudioRadio);
		
		// set type based on radio buttons
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


		// assign inputs to variables
		title = titleText.getText().toString();
		description = descText.getText().toString();
		creator = creatorText.getText().toString();
		try
		{
			numRequiredItems = Integer.parseInt(reqItemsText.getText().toString());
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
		Calendar submitDate = oldTask.getSubmitDate();
		
		if( completed == false && (task.getTaskItems().size() >= numRequiredItems)){
			completed = true;
		}

		// initialize toast
		// toast code from http://developer.android.com/guide/topics/ui/notifiers/toasts.html
		CharSequence toastText;
		Toast toast = null;

		// check if inputs are valid, and toast if they are not
		if (title.matches(""))
		{
			//TODO: use dialog boxes instead of toasts
			// display missing title error
			toastText = "Task title is required.";
			toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
			toast.show();
		}
		else if (creator.matches(""))
		{
			//TODO: use dialog boxes instead of toasts
			// display missing creator error
			toastText = "Creator is required.";
			toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
			toast.show();
		}
		else if (numRequiredItems < 1)
		{
			//TODO: use dialog boxes instead of toasts
			// display required items error
			toastText = "Required items must be > 0.";
			toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
			toast.show();
		}
		else
		{
			// create the task and store it
			Task task = new Task(oldTask.getId(), title, description, creator, numRequiredItems,
					completed, type, submitDate, deviceId);
			task.setTaskItems(oldTask.getTaskItems());
			
			sharedController.updateTask(context, task, position);
			toastText = "Task updated.";
			toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
			toast.show();
			this.finish();
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		//Check if returning from take image activity
		case (TAKE_IMAGE_CODE) : {
			if (resultCode == Activity.RESULT_OK) {
				//extract image from the intent
				Bundle extras = data.getExtras();
				Uri image = (Uri) extras.get("image");
				//add image to task
				ImageItem imageItem= new ImageItem(Calendar.getInstance(), image.getPath(), convertImageToByte(image));
				task.addTaskItem(imageItem);
			}
			break;
		} 
		}
	}
	//Converts a image Uri to a byte array for easier storage
	//Modified from http://colinyeoh.wordpress.com/2012/05/18/android-convert-image-uri-to-byte-array/
	public byte[] convertImageToByte(Uri uri){
		byte[] data = null;
		try {
			ContentResolver cr = getBaseContext().getContentResolver();
			InputStream inputStream = cr.openInputStream(uri);
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			data = baos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
}