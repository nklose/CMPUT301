//Code modifed from http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx

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
 * 
 * 
 * @author ChrisBeckett
 *
 */
public class SharedTaskBaseAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context context;
	
	public SharedTaskBaseAdapter(Context context) {
		this.context = context;
		mInflater = LayoutInflater.from(context.getApplicationContext());
	}

	public int getCount() {
		SharedTaskController sharedTaskController = NoNameApp.getSharedTaskController(this.context);
		return sharedTaskController.getNumberOfTasks();
	}

	public Object getItem(int position) {
		SharedTaskController sharedTaskController = NoNameApp.getSharedTaskController(this.context);
		return sharedTaskController.getTask(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		SharedTaskController sharedTaskController = NoNameApp.getSharedTaskController(this.context);
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
		Task entry = sharedTaskController.getTask(position);
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
