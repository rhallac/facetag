package PennApps.FaceTag;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends Activity {

	private Button loginButton;
	private Dialog progressDialog;
	static public String sAPI_KEY = "ulUhVjwOQE6RElyv";
	static public String sAPI_SECRET = "60UstwKt395ibLSw";
	static Context context;
	static String whoIsIt = "Stef";
	static String scoreStef, scoreRachel, scoreBecca;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Parse.initialize(this, "L6co5nhzzT7A9UvGcwAFbWV7WlSuw270GukGB0pq", "rXXwBQR639JmTTBm3cHbivV7NzKLRI09fmlYXWmV");     
	      //  ParseFacebookUtils.initialize("356060914569407");
	        String appsecret = "1C85B9DCF3C269DEC37A8E1454753ED8";
	        context = this;
	        
	        if(ParseUser.getCurrentUser() != null) {
	        	Intent i = new Intent(this, GameListActivity.class);
	        	startActivity(i);
	        }
		
	        setContentView(R.layout.activity_main);
		loginButton = (Button) findViewById(R.id.button1);
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onLoginButtonClicked();
			}
		});
		
		ParseUser.getQuery();
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereEqualTo("username", "Stef");
		query.findInBackground(new FindCallback<ParseUser>() {
		  public void done(List<ParseUser> objects, ParseException e) {
		    if (e == null) {
		    	if(objects.size() != 0) {
		        System.out.println("user was" + objects.get(0));
		    	scoreStef = ((Integer) objects.get(0).getInt("score")).toString();
		    	///stefScore.setText(scoreStef);
		    	}
		    	else System.out.println("no users :(");
		    } else {
		        // Something went wrong.
		    }
		  }
		}); 
		
		ParseUser.getQuery();
		ParseQuery<ParseUser> query2 = ParseUser.getQuery();
		query2.whereEqualTo("username", "Rachel");
		query2.findInBackground(new FindCallback<ParseUser>() {
		  public void done(List<ParseUser> objects, ParseException e) {
		    if (e == null) {
		    	if(objects.size() != 0) {
		        System.out.println("user was" + objects.get(0));
		        scoreRachel = ((Integer) objects.get(0).getInt("score")).toString();
		        System.out.println("beccascore: " + scoreRachel);
		       // rachelScore.setText(scoreRachel);
		    	}
		    	else System.out.println("no users :(");
		    } else {
		        // Something went wrong.
		    }
		  }
		});
		
		ParseUser.getQuery();
		ParseQuery<ParseUser> query3 = ParseUser.getQuery();
		query3.whereEqualTo("username", "Becca");
		query3.findInBackground(new FindCallback<ParseUser>() {
		  public void done(List<ParseUser> objects, ParseException e) {
		    if (e == null) {
		    	if(objects.size() != 0) {
		        System.out.println("user was" + objects.get(0));
		        scoreBecca = ((Integer) ((ParseUser) objects.get(0)).getInt("score")).toString();
		        System.out.println("beccascore: " + scoreBecca);
		      //  beccaScore.setText(scoreBecca);
		        
		    	}
		    	else System.out.println("no users :(");
		    } else {
		        // Something went wrong.
		    }
		  }
		});

		//let's test camera activity:
		/*Intent intent = new Intent(this, CameraActivity.class);
		startActivity(intent);*/
		
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
		loginUser(username, pass);
		//addUser(username, pass); 
		/*Intent intent = new Intent(this, GameListActivity.class);
		startActivity(intent);*/
	}
	
	public static void loginUser(String username, String password) {
		ParseUser.logInInBackground(username, password, new LogInCallback() {
			  public void done(ParseUser user, ParseException e) {
			    if (user != null) {
			      // Hooray! The user is logged in.
			    	 ParseFunctions.currentUser = ParseUser.getCurrentUser();
			 		//sample games
			 		ParseFunctions.addGame("Awesome Game", ParseUser.getCurrentUser());
			 		Intent intent = new Intent(context, GameListActivity.class);
			 		 context.startActivity(intent);
			 		
			    	System.out.println("yay log in success");
			    } else {
			      // Signup failed. Look at the ParseException to see what happened.
			    }
			  }
			});
	}
	
	public static void addUser(String name, String password) {
		  
		// ParseUser user = new ParseUser();
		 //ParseObject user = new ParseObject("User");
		 ParseUser user = new ParseUser();
		 user.setUsername(name);
		 user.setPassword(password);
		 user.setEmail(System.currentTimeMillis()+"@example.com");
		 int score = 0;
//		 user.put("name", name);
		 user.put("score", score);
		 user.put("games", new JSONArray());
		 System.out.println("inserting user");
		  
		 // other fields can be set just like with ParseObject
		// user.put("phone", "650-253-0000");
		  
		 user.signUpInBackground(new SignUpCallback() {
		   public void done(ParseException e) {
		     if (e == null) {
		    	 System.out.println("success");
		    	 Intent intent = new Intent(context, GameListActivity.class);
		 		 context.startActivity(intent);
		    	 ParseFunctions.currentUser = ParseUser.getCurrentUser();
		       // Hooray! Let them use the app now.
		     } else {
		    	 System.out.println("fail" + e);
		       // Sign up didn't succeed. Look at the ParseException
		       // to figure out what went wrong
		     }
		   }
		 }); 
	}
	

	/*private void showUserDetailsActivity() {
		Intent intent = new Intent(this, UserDetailsActivity.class);
		startActivity(intent);
	}*/
}