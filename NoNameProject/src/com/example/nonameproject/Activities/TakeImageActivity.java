package com.example.nonameproject.Activities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;

import com.example.nonameproject.R;
import com.example.nonameproject.Task;
import com.example.nonameproject.TextItem;
import com.example.nonameproject.R.layout;
import com.example.nonameproject.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TakeImageActivity extends Activity {

	private Uri imageUri;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 111;
	private String imageFilePath;
	private static final String TAG = "TakeImageActivity";
	private byte[] byteArray = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_image);

		imageUri = null;

		//Set up button to take a photo
		Button btnTakeImage = (Button) findViewById(R.id.newImageButton);
		btnTakeImage.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				//Launch photo taking method
				takeNewImage();
				Toast.makeText(getApplicationContext(), "Image taken.",
						Toast.LENGTH_LONG).show();
			}
		});
		//Set up button to save a taken photo
		Button btnSaveImage = (Button) findViewById(R.id.saveImageButton);
		btnSaveImage.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				//Check if a photo was taken
				if(imageUri == null){
					Toast.makeText(getApplicationContext(), "Image could not be saved. No image found",
							Toast.LENGTH_LONG).show();

				}
				else{
					Toast.makeText(getApplicationContext(), "Image saved.",
							Toast.LENGTH_LONG).show();
					//Pass image back
					Intent newIntent = new Intent();
					newIntent.putExtra("image", imageUri);
					setResult(Activity.RESULT_OK, newIntent);
					finish();
				}
			}
		});
		//Cancel taking a new image
		Button btnCancelImage = (Button) findViewById(R.id.cancelImageButton);
		btnCancelImage.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				//Delete photo if one was taken and not saved to the task
				if(imageUri != null){
					if(deleteFile(imageFilePath))
						Log.i(TAG, "Image deleted.");
				}
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_take_image, menu);
		return true;
	}

	//Responsible for launching the camera app
	private void takeNewImage(){
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		//Set up folder to store image
		String imageFolder = "/mnt/sdcard/tmp";
		File imageFileFolder = new File(imageFolder);
		if(!imageFileFolder.exists()){
			imageFileFolder.mkdir();
		}

		//Set up name for image
		imageFilePath = imageFolder + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
		File imageFile = new File(imageFilePath);
		imageUri = Uri.fromFile(imageFile);

		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

		//Start the camera activity
		startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		Log.i(TAG, "onActivityResult - requestCode: " + requestCode + " resultCode: " + resultCode);
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
			if (resultCode == RESULT_OK) {
				ImageView image = (ImageView) findViewById(R.id.imageView1);
				String path = imageUri.getPath();
				image.setImageDrawable(Drawable.createFromPath(path));
				/*
				ImageView image = (ImageView) findViewById(R.id.imageView1);
				Log.i(TAG, "Keys are " + intent.getExtras().keySet().toString());
				Bitmap photo = (Bitmap) intent.getExtras().get("data"); 
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
				byteArray = bytes.toByteArray();
				image.setImageBitmap(photo);
				*/
			}
		}
	}
}
