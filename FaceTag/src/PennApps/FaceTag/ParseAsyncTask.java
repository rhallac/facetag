package PennApps.FaceTag;


import android.content.Context;
import android.os.AsyncTask;
import com.parse.Parse;
import com.parse.ParseAnalytics;

public class ParseAsyncTask extends AsyncTask {

	@Override
	protected Object doInBackground(Object... arg0) {
		 Parse.initialize((Context) arg0[0], "L6co5nhzzT7A9UvGcwAFbWV7WlSuw270GukGB0pq", "rXXwBQR639JmTTBm3cHbivV7NzKLRI09fmlYXWmV");
		 
		return null;
	}

}