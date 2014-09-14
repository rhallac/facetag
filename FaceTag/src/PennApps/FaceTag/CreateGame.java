package PennApps.FaceTag;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CreateGame extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_game, menu);
		return true;
	}
	
	public void onClick(View v){
		//check which button is clicked
		if (v.getId() == R.id.creategame){
			ParseFunctions.addGame(R.id.GameName + "", ParseFunctions.currentUser.toString());
			ParseFunctions.addUserToGame(R.id.username1 + "", R.id.GameName + "");
			Intent start = new Intent(getApplicationContext(), GameListActivity.class);
			startActivity(start);
		}
	}

}
