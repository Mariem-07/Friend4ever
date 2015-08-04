package tn.insat.iac;

import java.util.ArrayList;
import java.util.List;

import tn.insat.iac.model.Friend;
import tn.insat.iac.service.FriendService;
import tn.insat.iac.service.imp.FriendServiceImp;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFriendActivity extends Activity {

	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_friend);

		listView = (ListView) findViewById(R.id.listView);
		FriendAsyncTask asyncTask = new FriendAsyncTask();
		asyncTask.execute();

	}

	private class FriendAsyncTask extends AsyncTask<Void, Long, List<Friend>> {

		@Override
		protected List<Friend> doInBackground(Void... params) {

			FriendService service = new FriendServiceImp();
			List<Friend> Friends = service.getAllFriends();
			return Friends;
		}

		@Override
		protected void onPostExecute(List<Friend> friends) {

			ArrayList<String> names = new ArrayList<String>();

			for (Friend friend : friends) {
				names.add(friend.getFirstname() + " " + friend.getLastname());
			}
			listView.setAdapter(new ArrayAdapter<String>(
					ListFriendActivity.this, R.layout.text_view, names));
		}

	}
}
