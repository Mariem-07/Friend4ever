package tn.insat.iac;

import tn.insat.iac.model.Friend;
import tn.insat.iac.service.FriendService;
import tn.insat.iac.service.imp.FriendServiceImp;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	private EditText firstnameEditText;
	private EditText lastnameEditText;
	private EditText mailEditText;
	private Button saveBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		firstnameEditText = (EditText) findViewById(R.id.firstname);
		lastnameEditText = (EditText) findViewById(R.id.lastname);
		mailEditText = (EditText) findViewById(R.id.mail);

		saveBtn = (Button) findViewById(R.id.save);
		saveBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				RegisterAsyncTask asyncTask = new RegisterAsyncTask();
				asyncTask.execute();
			}
		});

	}

	private class RegisterAsyncTask extends AsyncTask<Void, Long, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {

			FriendService service = new FriendServiceImp();
			String firstname = firstnameEditText.getText().toString();
			String lastname = lastnameEditText.getText().toString();
			String mail = mailEditText.getText().toString();
			Friend friend = new Friend();
			friend.setFirstname(firstname);
			friend.setLastname(lastname);
			friend.setMail(mail);
			boolean response = service.addFriend(friend);
			return response;
		}

		@Override
		protected void onPostExecute(Boolean response) {
			if (response) {
				Intent intent = new Intent(RegisterActivity.this,
						ListFriendActivity.class);
				startActivity(intent);
			}
		}

	}
}
