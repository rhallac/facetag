package PennApps.FaceTag;

import java.util.List;

import org.json.JSONException;

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
		List<ParseUser> games = ParseFunctions.getGamesForUser(currentUserName);
		
		for(int i = 0; i < games.size(); i++) {
			LinearLayout gameBlock = new LinearLayout(this); 
			LayoutInflater vi = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gameBlock = (LinearLayout) vi.inflate(R.layout.group_block, null);
			//int score = games.get(i).getInt("");
			String name = games.get(i).getString("name");
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

}
