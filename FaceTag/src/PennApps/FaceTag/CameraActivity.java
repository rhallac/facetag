package PennApps.FaceTag;

import java.io.ByteArrayOutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888; 
    private ImageView imageView;
    Context context;
    public String taggedName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        context = this;
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                startActivityForResult(cameraIntent, CAMERA_REQUEST); 
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
            imageView.setImageBitmap(photo);
            
      	  ParseUser user = ParseUser.getCurrentUser();
		  //update the score
		  //int score = (Integer) user.get("score");
		  user.increment("score");
		  user.saveInBackground();
            
            Toast.makeText(this, "Please wait a moment...", Toast.LENGTH_LONG);
            
            //and do face rec stuff here
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            String base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
            byte[] decode = Base64.decode(base64Image, Base64.DEFAULT);
            
            
            String string = "{ api_key : '" +  LoginActivity.sAPI_KEY + "', " +
            		"api_secret : '" + LoginActivity.sAPI_SECRET + "', jobs: '_nodetect',"
            		+"base64 : '"+ base64Image + "' }";
            
         //    System.out.println("string is: " + string);
           // RekoSDK.face_recognize(string, callback);
            RekoSDK.face_recognize(decode, callback);
         //   Intent i = new Intent(this, UserListActivity.class);
           // startActivity(i);
          // RekoSDK.face_add("Becca", decode, callback);
         //   RekoSDK.face_train(callback);
            		
            		//places: ['Africa', 'America', 'Asia', 'Australia'] }";
            
        }  
    }  
    
    RekoSDK.APICallback callback = new RekoSDK.APICallback() { 
        public void gotResponse(String sResponse) {
            System.out.print("omg omg omg " + sResponse);
            try {
            	if(sResponse != null) {
				JSONObject result = new JSONObject(sResponse);
				if(result != null) {
					/*JSONArray facedetect = (JSONArray) result.get("face_detection");
					if(facedetect != null) {
						JSONArray  = (JSONArray) result.get("matches");
						JSONArray matches = (JSONArray) facedetect.get(3);
				if(matches != null) {
					JSONObject match = (JSONObject) matches.get(0);
					if(match != null) {
					String tag = match.getString("tag");
					System.out.println("YES TAG IS" + tag);
					String tagtext = ("Tag!" + tag + "is it!");
					Toast.makeText(context, tagtext.toString(), Toast.LENGTH_LONG).show();
					}
				}   
					}
				}
				}*/
					int ind = sResponse.indexOf("name");
					
					String tag = sResponse.substring(ind+7, sResponse.length()-ind-1);
					System.out.println("ta before is" + tag);
					int end = tag.indexOf(":");
					System.out.println("index before and after" + ind + " "+ end);
					tag = tag.substring(0, end);
					System.out.println("tag substring is" + tag);
					
					System.out.println("YES TAG IS" + tag);
					final String tagtext = ("Tag!" + tag + "is it!");
					taggedName = tagtext;
					Looper.prepare();
					LoginActivity.whoIsIt = tagtext;
				
					((Activity) context).runOnUiThread(new Runnable() {
						  public void run() {
							  Toast.makeText(context, tagtext.toString(), Toast.LENGTH_LONG).show();
					
							  Intent i = new Intent(context, UserListActivity.class);
					          startActivity(i);
						  }
						});
					}
            	}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
        }
    };
   
}