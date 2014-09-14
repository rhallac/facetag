package PennApps.FaceTag;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.os.Looper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class UserListActivity extends Activity {
	String currentGameName;
	/*String scoreStef = "8";
	String scoreRachel = "9";
	String scoreBecca = "10";*/
	TextView stefScore;
	TextView rachelScore;
	TextView beccaScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		//currentGameName = ParseFunctions.currentGam
		currentGameName = "Awesome Game";
		
		
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.user_listview);
		/*List<String> users = ParseFunctions.getUsersForGame(currentGameName);
		System.out.println("user size in listpage is" + users.size());
		
		for(int i = 0; i < users.size(); i++) {
			LinearLayout userBlock = new LinearLayout(this); 
			LayoutInflater vi = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			userBlock = (LinearLayout) vi.inflate(R.layout.user_block, null);
			//int score = users.get(i).getInt("score");
			String name = users.get(i).getString("username");
			((TextView) userBlock.findViewById(R.id.userScore)).setText(score);
			((TextView) userBlock.findViewById(R.id.userName)).setText(users.get(i));
		}*/
		
		
		

		//Hard coded:
		LinearLayout userBlock = new LinearLayout(this); 
		LayoutInflater vi = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		userBlock = (LinearLayout) vi.inflate(R.layout.user_block, null);
		beccaScore = (TextView) userBlock.findViewById(R.id.userScore);
		((TextView) userBlock.findViewById(R.id.userScore)).setText(LoginActivity.scoreBecca);
		((TextView) userBlock.findViewById(R.id.userName)).setText("Becca");
		layout.addView(userBlock); 
		
		LinearLayout userBlock2 = new LinearLayout(this); 
		LayoutInflater vi2 = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		userBlock2 = (LinearLayout) vi2.inflate(R.layout.user_block, null);
		rachelScore = (TextView) userBlock.findViewById(R.id.userScore);
		((TextView) userBlock2.findViewById(R.id.userScore)).setText(LoginActivity.scoreRachel);
		((TextView) userBlock2.findViewById(R.id.userName)).setText("Rachel");
		layout.addView(userBlock2);
		
		LinearLayout userBlock3 = new LinearLayout(this); 
		LayoutInflater vi3 = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		userBlock3 = (LinearLayout) vi3.inflate(R.layout.user_block, null);
		//String score = ((Integer) ParseUser.getCurrentUser().getInt("score")).toString();
		stefScore = (TextView) userBlock.findViewById(R.id.userScore);
		((TextView) userBlock3.findViewById(R.id.userScore)).setText(LoginActivity.scoreStef);
		((TextView) userBlock3.findViewById(R.id.userName)).setText("Stef");
		layout.addView(userBlock3);
		
		
		
		((Button)findViewById(R.id.camera_click)).setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				System.out.println("CLICLED CAMERA");
				Intent start = new Intent(getApplicationContext(), CameraActivity.class);
				startActivity(start);
				
			}
		});
	
	}
	
	public boolean onCameraClick(View v) {
		Intent intent = new Intent(this, CameraActivity.class);
		startActivity(intent);
		return true;
	}
		 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

}
