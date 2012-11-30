package network;

import java.util.ArrayList;

import com.example.nonameproject.SharedIOAdapter;
import com.example.nonameproject.SharedTaskController;
import com.example.nonameproject.Task;

import android.os.AsyncTask;

public class GetSharedTasksThread extends AsyncTask<SharedTaskController, Void, Void> {

	@Override
	protected Void doInBackground(SharedTaskController... controller) {
		try {
			ArrayList<Task> tasks = new ArrayList<Task>();
			tasks = SharedIOAdapter.getSharedTasks();
			controller[0].addAllTasks(tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected void onPostExecute(){
		
	}

}
