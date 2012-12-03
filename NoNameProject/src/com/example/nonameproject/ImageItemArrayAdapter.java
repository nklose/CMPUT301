/**
 * http://devtut.wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/
 */
package com.example.nonameproject;

import java.text.SimpleDateFormat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageItemArrayAdapter extends ArrayAdapter<Object>{
	Context context;
	int layoutResourceId;
	Object[] taskItems;
	
	public ImageItemArrayAdapter(Context context, int layoutResourceId, Object[] taskItems){
		super(context, layoutResourceId, taskItems);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.taskItems = taskItems;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View view = convertView;
		
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.custom_image_task_item_row, null);
		}
		
		//get the imageItem
		ImageItem imageItem = (ImageItem) taskItems[position];
		
		if(imageItem != null){
			TextView dateView = (TextView) view.findViewById(R.id.textViewDate);
			ImageView imageView = (ImageView) view.findViewById(R.id.imageItemView);
			
			//Set the date
			String date = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

			if (imageItem.getSubmitDate() != null) {
				date = dateFormat.format(imageItem.getSubmitDate().getTime());
			}
			dateView.setText(date);
			//Decode the byte array to the image
			Bitmap bMap = BitmapFactory.decodeByteArray(imageItem.getImageData(), 0, imageItem.getImageData().length);
			imageView.setImageBitmap(bMap);
		}
		return view;
	}
}
