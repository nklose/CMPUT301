package network;

import android.os.AsyncTask;

import com.example.nonameproject.SharedIOAdapter;
import com.example.nonameproject.Task;

public class DeleteSharedTaskThread extends AsyncTask<Task, Void, Void> {

	@Override
	protected Void doInBackground(Task... task) {
		try {
			SharedIOAdapter.delete(task[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
