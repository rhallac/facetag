package PennApps.FaceTag;


import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class ParseAddUser extends AsyncTask {

	@Override
	protected Object doInBackground(Object... arg0) {
		 Parse.initialize((Context) arg0[0], "L6co5nhzzT7A9UvGcwAFbWV7WlSuw270GukGB0pq", "rXXwBQR639JmTTBm3cHbivV7NzKLRI09fmlYXWmV");
		 
		 //input: context, name, e-mail, score
		 String name = (String) arg0[1];
		 String email = (String) arg0[2];
		 int score = (Integer) arg0[3];
		 String id = name+System.currentTimeMillis();
		  
		 ParseObject user = new ParseObject("User");
	
		 user.put("name", name);
		 user.put("email", email);
		 user.put("score", score);
		 user.put("id",  id);
		 user.saveInBackground();
		 
		return user;
	}

}