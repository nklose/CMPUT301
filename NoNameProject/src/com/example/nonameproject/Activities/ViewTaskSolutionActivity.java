package com.example.nonameproject.Activities;

import com.example.nonameproject.CompletedTaskController;
import com.example.nonameproject.NoNameApp;
import com.example.nonameproject.R;
import com.example.nonameproject.R.layout;
import com.example.nonameproject.Task;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ViewTaskSolutionActivity extends Activity {

	private int position;	
	private CompletedTaskController controller = NoNameApp.getCompletedTaskController();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_solution);
        position = savedInstanceState.getInt("position");
        Task task = controller.getTask(position);
        TextView textView = (TextView) findViewById(R.id.solutionTaskTitle);
    	textView.setText(task.getTitle());
        textView = (TextView) findViewById(R.id.solutionTaskDesc);
    	textView.setText(task.getDescription());
        textView = (TextView) findViewById(R.id.solutionTaskCreator);
    	textView.setText(task.getCreator());
    	Task.TaskType type = task.getType();
    	String typeText = "";
    	if (type == Task.TaskType.TASK_TEXT){
    		typeText = "Text";
    	} else if (type == Task.TaskType.TASK_IMAGE){
    		typeText = "Image";
    	} else if (type == Task.TaskType.TASK_AUDIO){
    		typeText = "Audio";
    	}
        textView = (TextView) findViewById(R.id.solutionTaskType);
    	textView.setText(typeText);
        textView = (TextView) findViewById(R.id.solutionTaskItemsRequested);
    	textView.setText(task.getNumRequiredItems());
    }
}
