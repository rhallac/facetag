package PennApps.FaceTag;

import org.json.JSONArray;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseFunctions {

	public static String addUser(String name, String email) {
		 String id = name+System.currentTimeMillis();
		  
		 ParseObject user = new ParseObject("User");
		 int score = 0;
		 user.put("name", name);
		 user.put("email", email);
		 user.put("score", score);
		 user.put("id",  id);
		 user.saveInBackground();
		 
		 return id;
		 
	}
	
	public static String addGame(String name, String creatorId) {
		 String id = name+System.currentTimeMillis();
		  
		 ParseObject user = new ParseObject("Game");
		 JSONArray users = new JSONArray();
		 users.put(creatorId);
		 
		 JSONArray images = new JSONArray();
		  
		 user.put("name", name);
		 user.put("email", creatorId);
		 user.put("id",  id);
		 user.put("users", users);
		 user.put("images", images);
		 user.saveInBackground();
		 
		 return id;
		 
	}
}
