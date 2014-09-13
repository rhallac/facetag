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
	private List<ParseUser> games = new ArrayList<ParseUser>();

	public static void addUser(String name) {
		  
		 ParseUser user = new ParseUser();
		 int score = 0;
		 user.put("name", name);
		 user.put("score", score);
		 user.saveInBackground();
		 
		 
	}
	
	public static void addGame(String name, String creatorId) {
		 String id = name+System.currentTimeMillis();
		  
		 ParseObject user = new ParseObject("Game");
		 JSONArray users = new JSONArray();
		 users.put(creatorId);
		 
		 JSONArray images = new JSONArray();
		  
		 user.put("name", name);
		 user.put("users", users);
		 user.saveInBackground();
		 
		 
	}
	
	public void addUserToGame(final String userName, String gameName) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Game");
		query.whereEqualTo("name", gameName);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
		  public void done(List<ParseObject> objects, ParseException e) {
		    if (e == null) {
		        // The query was successful.
		    	if(objects.size() != 0) {
		    		JSONArray users = (JSONArray) objects.get(0).get("users");
		    		users.put(userName);
		    	}
		    
		    } else {
		        // Something went wrong.
		    	System.out.println("something went wrong when adding user to game" + e);
		    	
		    }
			};
		});
	}
	
	
	public void changeUserScore(String facebookid, int score) {
		
	}

	
	public List<ParseUser> getGamesForUser(String facebookId) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		//List<ParseUser> games = new ArrayList<ParseUser>();
		query.whereEqualTo("id", facebookId);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
		  public void done(List<ParseObject> objects, ParseException e) {
		    if (e == null) {
		        // The query was successful.
		    	//now get users
		    	if(objects.size() != 0) {
		    		 JSONArray gamesArray = (JSONArray) objects.get(0).get("game");
		    		 //List<ParseUser> games = new ArrayList<ParseUser>();
		    		 for(int i = 0; i < gamesArray.length(); i++){
		    			try {
							games.add((ParseUser) gamesArray.get(i));
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
		return games;
	}

	
	public void getUsersForGame(String gameName) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Game");
		query.whereEqualTo("name", gameName);
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
