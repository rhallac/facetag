package PennApps.FaceTag;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        Parse.initialize(this, "L6co5nhzzT7A9UvGcwAFbWV7WlSuw270GukGB0pq", "rXXwBQR639JmTTBm3cHbivV7NzKLRI09fmlYXWmV");     
        setContentView(R.layout.activity_main);
        ParseFacebookUtils.initialize("YOUR FACEBOOK APP ID");
        
       // ParseFunctions.addGame("new game", "123");
       // ParseFunctions.addUser("newuser", "123@123.com");
        
        //new ParseAddUser().execute(this, "bob", "bob@bob", 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
 