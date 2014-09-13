package PennApps.FaceTag;


import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class ParseAddGame extends AsyncTask {

	@Override
	protected Object doInBackground(Object... arg0) {
		 Parse.initialize((Context) arg0[0], "L6co5nhzzT7A9UvGcwAFbWV7WlSuw270GukGB0pq", "rXXwBQR639JmTTBm3cHbivV7NzKLRI09fmlYXWmV");
		 
		 //input: context, name, creator id
		 String name = (String) arg0[1];
		 String creatorId = (String) arg0[2];
		 String id = name+System.currentTimeMillis();
		 
		 JSONArray users = new JSONArray();
		 users.put(creatorId);
		 
		 JSONArray images = new JSONArray();
		  
		 ParseObject user = new ParseObject("User");

		 user.put("name", name);
		 user.put("creatorId", creatorId);
		 user.put("id", id);
		 user.put("id",  id);
		 user.put("users", users);
		 user.put("images", images);
		 
		 user.saveInBackground();
		 
		return user;
	}

}