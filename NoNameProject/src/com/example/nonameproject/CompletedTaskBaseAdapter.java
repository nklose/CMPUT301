package com.example.nonameproject;

import java.text.SimpleDateFormat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * An implementation of BaseAdapter to be used with a Task.  
 * Task entries are formatted as appropriate into the layout 'custom_log_row_view.xml'
 * Implementation uses modified code from:
 * 	http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx
 */
public class CompletedTaskBaseAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context context;
	
	/**
	 * Creates a CompletedTaskBaseAdapter from the given context.
	 * @param context Current context.
	 */
	public CompletedTaskBaseAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
		this.context = context;
	}

	/**
	 * Returns the number of completed tasks.
	 * @return Number of completed tasks.
	 */
	public int getCount() {
		CompletedTaskController completedTaskController = NoNameApp.getCompletedTaskController(context);
		return completedTaskController.getNumberOfTasks();
	}

	/**
	 * Gets the object at a given position.
	 * @param position Position to get the object from.
	 * @return The object at the given position.
	 */
	public Object getItem(int position) {
		CompletedTaskController completedTaskController = NoNameApp.getCompletedTaskController(context);
		return completedTaskController.getTask(position);
	}

	/**
	 * Gets the ID of the item at the given position.
	 * @param position Position to get the ID of.
	 * @return the ID of the item at the given position.
	 */
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		CompletedTaskController completedTaskController = NoNameApp.getCompletedTaskController(context);
		ViewHolder holder = holder(convertView);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_local_task_row, null);
			holder.txtDate = (TextView) convertView.findViewById(R.id.textViewDate);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
			holder.txtType = (TextView) convertView.findViewById(R.id.textViewType);
			holder.txtCreator = (TextView) convertView.findViewById(R.id.textViewCreator);
			holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBarTaskItems);

			convertView.setTag(holder);
		} else {
		}
		Task entry = completedTaskController.getTask(position);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String submitDate = simpleDateFormat.format(entry.getSubmitDate().getTime());
		holder.txtDate.setText(submitDate);
		holder.txtTitle.setText(entry.getTitle());
		Task.TaskType type = entry.getType();
    	String typeText = "";
    	if (type == Task.TaskType.TASK_TEXT){
    		typeText = "Text";
    	} else if (type == Task.TaskType.TASK_IMAGE){
    		typeText = "Image";
    	} else if (type == Task.TaskType.TASK_AUDIO){
    		typeText = "Audio";
    	}
		holder.txtType.setText(typeText);
		holder.txtCreator.setText(entry.getCreator());
		
		int progress = entry.getTaskItems().size() / entry.getNumRequiredItems();
		holder.progressBar.setProgress(progress);

		return convertView;
	}

	/**
	 * Holds objects needed for the view.
	 * @param convertView The view.
	 * @return The ViewHolder.
	 */
	private ViewHolder holder(View convertView) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return holder;
	}

	static class ViewHolder {
		TextView txtTitle;
		TextView txtType;
		TextView txtCreator;
		TextView txtDate;
		ProgressBar progressBar;
	}
}
