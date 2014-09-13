package PennApps.FaceTag;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_list);
	}

	
	public void getGames(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
		query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
			    if (e == null) {
			    	int score = object.getInt("score");
			    	String playerName = object.getString("playerName");
			    	boolean cheatMode = object.getBoolean("cheatMode");
			      // object will be your game score
			    } else {
			      // something went wrong
			    }
			  }
			});
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

}
