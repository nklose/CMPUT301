package com.example.nonameproject;

import java.text.SimpleDateFormat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * An implementation of BaseAdapter to be used with a TaskItem.  
 * Task items are formatted as appropriate into the layout 'task_item_row_view.xml'
 */
public class LocalTaskItemBaseAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	Task task;
	
	public LocalTaskItemBaseAdapter(Context context, Task task) {
		mInflater = LayoutInflater.from(context);
		this.task = task;
	}
	
	// return the number of task items for the given task
	public int getCount() {
		return task.getTaskItems().size();
	}

	// get the task item at a given position
	public Object getItem(int position) {
		return task.getTaskItems().get(position);
	}

	// get the task item id at a given position
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		
		if (convertView == null) 
		{
			convertView = mInflater.inflate(R.layout.custom_text_item_layout, null);
			vh = new ViewHolder();
			vh.txtItem = (TextView) convertView.findViewById(R.id.textItemDescription);
			vh.txtDate = (TextView) convertView.findViewById(R.id.textItemDate);

			convertView.setTag(vh);
		} 
		else 
		{
			vh = (ViewHolder) convertView.getTag();
		}
		
		if (task.getType() == Task.TaskType.TASK_TEXT)
		{
			
			TextItem textItem = (TextItem) task.getTaskItems().get(position);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String submitDate = simpleDateFormat.format(textItem.getSubmitDate().getTime());
			vh.txtDate.setText(submitDate);
			vh.txtItem.setText(textItem.getDescription());
		}

		return convertView;
	}
	
	static class ViewHolder {
		TextView txtItem;
		TextView txtDate;
	}
	
}
