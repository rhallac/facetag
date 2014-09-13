package PennApps.FaceTag;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.GetCallback;
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

public class UserListActivity extends Activity {
	String currentGameName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		try {
			currentGameName = ParseFunctions.currentGame.getString("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ListView layout = (ListView) findViewById(R.id.user_listview);
		List<ParseUser> users = ParseFunctions.getUsersForGame(currentGameName);
		
		for(int i = 0; i < users.size(); i++) {
			LinearLayout userBlock = new LinearLayout(this); 
			LayoutInflater vi = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			userBlock = (LinearLayout) vi.inflate(R.layout.user_block, null);
			int score = users.get(i).getInt("score");
			String name = users.get(i).getString("username");
			((TextView) userBlock.findViewById(R.id.userScore)).setText(score);
			((TextView) userBlock.findViewById(R.id.userName)).setText(name);
		}
		
		
		
	}
		 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

}
