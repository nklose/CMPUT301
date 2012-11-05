//Code modifed from http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx

package com.example.nonameproject;

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
public class LocalTaskBaseAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	
	public LocalTaskBaseAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		LocalTaskController localTaskController = NoNameApp.getLocalTaskController();
		return localTaskController.getNumberOfTasks();
	}

	public Object getItem(int position) {
		LocalTaskController localTaskController = NoNameApp.getLocalTaskController();
		return localTaskController.getTask(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LocalTaskController localTaskController = NoNameApp.getLocalTaskController();
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_local_task_row, null);
			holder = new ViewHolder();
			holder.txtDate = (TextView) convertView.findViewById(R.id.textViewDate);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
			holder.txtType = (TextView) convertView.findViewById(R.id.textViewType);
			holder.txtCreator = (TextView) convertView.findViewById(R.id.textViewCreator);
			holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBarTaskItems);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Task entry = localTaskController.getTask(position);
		holder.txtDate.setText(entry.getSubmitDate().toString());
		holder.txtTitle.setText(entry.getTitle());
		holder.txtType.setText(entry.getType());
		holder.txtCreator.setText(entry.getCreator());
		
		int progress = entry.getTaskItems().size() / entry.getNumRequiredItems();
		holder.progressBar.setProgress(progress);

		return convertView;
	}

	static class ViewHolder {
		TextView txtTitle;
		TextView txtType;
		TextView txtCreator;
		TextView txtDate;
		ProgressBar progressBar;
	}
}