package PennApps.FaceTag;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseFunctions {

	/*public static void addUser(String name, String email, String facebookId) {
		  
		 ParseObject user = new ParseObject("User");
		 int score = 0;
		 user.put("name", name);
		 user.put("email", email);
		 user.put("score", score);
		 user.put("id",  facebookId);
		 user.saveInBackground();
		 
		 
	}*/
	
	public static void addGame(String name, String creatorId) {
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
		 
		 
	}
	
	public void addUserToGame(String facebookId, String GameId) {
		
	}
	
	public void changeUserScore(String facebookid, int score) {
		
	}
	
	public void addImageToGame() {
		
	}
	
	public void getGamesForUser(String facebookId) {
		
	}
	
	public void getUsersForGame(String gameId) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Game");
		query.whereEqualTo("id", gameId);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
		  public void done(List<ParseObject> objects, ParseException e) {
		    if (e == null) {
		        // The query was successful.
		    	//now get users
		    	if(objects.size() != 0) {
		    		 JSONArray usersArray = (JSONArray) objects.get(0).get("users");
		    		 List<ParseUser> users = new ArrayList<ParseUser>();
		    		 for(int i = 0; i < usersArray.length(); i++){
		    			try {
							users.add((ParseUser) usersArray.get(i));
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    		 }
		    	}
		    } else {
		        // Something went wrong.
		    }
		  }

		});
	}
	
	public void getImagesForGame(String gameId) {
		
	}
	
}
