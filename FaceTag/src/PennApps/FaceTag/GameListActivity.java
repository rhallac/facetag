package PennApps.FaceTag;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
//lists games and user profile

public class GameListActivity extends Activity {
	String currentUserName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_page);
		if(ParseUser.getCurrentUser() != null) {
		currentUserName = ParseUser.getCurrentUser().getString("name");
		String score = ParseFunctions.currentUser.getString("score");
		System.out.print("score is" + score);
		
		((TextView) findViewById(R.id.userName)).setText(currentUserName);
		((TextView) findViewById(R.id.userScore)).setText(score);
		
		
		ListView layout = (ListView) findViewById(R.id.user_listview);
		JSONArray games = ParseFunctions.getGamesForUser(currentUserName);
		System.out.println("game size is" + games.length());
		
		for(int i = 0; i < games.length(); i++) {
			
			LinearLayout gameBlock = new LinearLayout(this); 
			LayoutInflater vi = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gameBlock = (LinearLayout) vi.inflate(R.layout.group_block, null);
			//int score = games.get(i).getInt("");
			String name = "not working this is default oops";
			try {
				name = ((JSONObject) games.get(i)).getString("name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//((TextView) userBlock.findViewById(R.id.userScore)).setText(score);
			((TextView) gameBlock.findViewById(R.id.groupName)).setText(name);
		}
		}
		
	}
		 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

	public void onClick(View v){
		//check which button is clicked
	/*	if (v.getId() == R.id.quit){
		}
		}*/
}
}
