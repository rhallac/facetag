package PennApps.FaceTag;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

	private Button loginButton;
	private Dialog progressDialog;
	static public String sAPI_KEY = "ulUhVjwOQE6RElyv";
	static public String sAPI_SECRET = "60UstwKt395ibLSw";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Parse.initialize(this, "L6co5nhzzT7A9UvGcwAFbWV7WlSuw270GukGB0pq", "rXXwBQR639JmTTBm3cHbivV7NzKLRI09fmlYXWmV");     
	      //  ParseFacebookUtils.initialize("356060914569407");
	        String appsecret = "1C85B9DCF3C269DEC37A8E1454753ED8";
		
	        setContentView(R.layout.activity_main);
		loginButton = (Button) findViewById(R.id.button1);
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onLoginButtonClicked();
			}
		});

		//let's test camera activity:
		Intent intent = new Intent(this, CameraActivity.class);
		startActivity(intent);
		
		// Check if there is a currently logged in user
		// and they are linked to a Facebook account.
		ParseUser currentUser = ParseUser.getCurrentUser();
		/*if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
			// Go to the user info activity
			showUserDetailsActivity();
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}

	private void onLoginButtonClicked() {
		String username = (String) ((EditText) findViewById(R.id.editText1)).getText().toString();
		String pass = (String) ((EditText) findViewById(R.id.editText2)).getText().toString();
		ParseFunctions.addUser(username, pass);
		Intent intent = new Intent(this, GameListActivity.class);
		startActivity(intent);
	}
	

	/*private void showUserDetailsActivity() {
		Intent intent = new Intent(this, UserDetailsActivity.class);
		startActivity(intent);
	}*/
}