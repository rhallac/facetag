package PennApps.FaceTag;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ParseFunctions {
	//private static List<ParseUser> games = new ArrayList<ParseUser>();
	public static ParseUser currentUser;
	public static JSONObject currentGame;
	static List<String> users = new ArrayList<String>();
	static ArrayList gamesArray;

	//moved to loginactivity
	/*public static void addUser(String name, String password) {
		  
		// ParseUser user = new ParseUser();
		 //ParseObject user = new ParseObject("User");
		 ParseUser user = new ParseUser();
		 user.setUsername(name);
		 user.setPassword(password);
		 user.setEmail(System.currentTimeMillis()+"@example.com");
		 int score = 0;
		 user.put("name", name);
		 user.put("score", score);
		 user.put("games", new JSONArray());
		 System.out.println("inserting user");
		  
		 // other fields can be set just like with ParseObject
		// user.put("phone", "650-253-0000");
		  
		 user.signUpInBackground(new SignUpCallback() {
		   public void done(ParseException e) {
		     if (e == null) {
		    	 System.out.println("success");
		    	 currentUser = ParseUser.getCurrentUser();
		       // Hooray! Let them use the app now.
		     } else {
		    	 System.out.println("fail" + e);
		       // Sign up didn't succeed. Look at the ParseException
		       // to figure out what went wrong
		     }
		   }
		 }); 
	}*/
	
	public static void loginUser(String username, String password) {
		ParseUser.logInInBackground(username, password, new LogInCallback() {
			  public void done(ParseUser user, ParseException e) {
			    if (user != null) {
			      // Hooray! The user is logged in.
			    	System.out.println("yay log in success");
			    } else {
			      // Signup failed. Look at the ParseException to see what happened.
			    }
			  }
			});
	}
	
	public static void addGame(String name, ParseUser user) {
		  
		 ParseObject game = new ParseObject("Game");
		 JSONArray users = new JSONArray();
		 users.put(user.getUsername());
		  
		 game.put("name", name);
		 game.put("users", users);
		 
	
			    		System.out.println("adding game user is" + user.getUsername());
			    		//???!!!
			    		ArrayList games = (ArrayList) user.get("games");
			    		games.add(name);
			    		user.put("games", games);
			    		game.saveInBackground();
			    		user.saveInBackground();
		 
		 
	}
	
	public static void addUserToGame(final String userName, String gameName) {
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

	
	public static ArrayList getGamesForUser(String username) {
		
		//get user from username
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		//List<ParseUser> games = new ArrayList<ParseUser>();
		query.whereEqualTo("username", username);
		query.findInBackground(new FindCallback<ParseUser>() {
			@Override
		  public void done(List<ParseUser> objects, ParseException e) {
		    if (e == null) {
		        // The query was successful.
		    	//now get users
		    	if(objects.size() != 0) {
		    		//get games
		    		 gamesArray = (ArrayList) objects.get(0).get("games");
		    		 //List<ParseUser> games = new ArrayList<ParseUser>();
		    		/* for(int i = 0; i < gamesArray.length(); i++){
		    			try {
							//games.add((ParseObject) gamesArray.get(i));
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    		 }
		    	}
		    } else {
		        // Something went wrong.
		    }
		  }*/
		    	}
		    }
		    }
		});
		if(gamesArray != null) return gamesArray;
		return new ArrayList();
	}

	
	
	public static List getUsersForGame(String gameName) {
		System.out.println("about to get suers for gamae");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Game");
		query.whereEqualTo("name", gameName);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
		  public void done(List<ParseObject> objects, ParseException e) {
		    if (e == null) {
		        // The query was successful.
		    	//now get users
		    	if(objects.size() != 0) {
		    		 ArrayList usersArray = (ArrayList) objects.get(0).get("users");
		    		 System.out.println("user size is" + usersArray.size());
		    		 users = usersArray;
		    		 
		    		 /*for(int i = 0; i < usersArray.size(); i++){
		    			users.add((String) usersArray.get(i));
		    		 }*/
		    	}
		    } else {
		        // Something went wrong.
		    }
		  }

		});
		return users;
	}
	
	public void getImagesForGame(String gameId) {
		
	}
	
	

	//takes game name and user name and increment score --> CHECK
	public static void checkUserGame(String gameName, String userName) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Game");
		query.whereEqualTo("name", gameName);
		query.whereEqualTo("username", userName);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
		  public void done(List<ParseObject> objects, ParseException e) {
		    if (e == null) {
		        // The query was successful.
		    	if(objects.size() != 0) {
		    		ParseObject gameScore = new ParseObject("score");
		    		gameScore.increment("score");
		    	}
		    
		    } else {
		        // Something went wrong.
		    	System.out.println("incrementing score" + e);
		    	
		    }
			};
		});
	}
	
	
	
	
	
	
	
	
	
}
