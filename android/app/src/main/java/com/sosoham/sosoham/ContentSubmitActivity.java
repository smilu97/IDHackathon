package com.sosoham.sosoham;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sosoham.sosoham.sosodb.FaceBook;
import com.sosoham.sosoham.sosodb.SOSODB;

import org.json.JSONException;
import org.json.JSONObject;

public class ContentSubmitActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private String myId;
    public SOSODB sosodb = new SOSODB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        // myId = intent.getStringExtra("myId");
        myId = FaceBook.my_id;
        setContentView(R.layout.activity_content_submit);
        ImageView subButton = (ImageView)findViewById(R.id.submitSubmitButton);

        findViewById(R.id.submitSubmitButton).setOnClickListener(submitListener);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    Button.OnClickListener submitListener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView titleText = (TextView)findViewById(R.id.submitTitleText);
            TextView contentText = (TextView)findViewById(R.id.submitContentText);
            String contentString = contentText.getText().toString();
            //TODO : Upload db (fbId, contentString)
            JSONObject sendObj = new JSONObject();
            try {
                sendObj.put("my_id", myId);
                sendObj.put("method", "add_hope");
                sendObj.put("gift_name", titleText.getText());
                sendObj.put("gift_content", contentText.getText());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sosodb.add(sendObj, new SOSODB.SOSODBListener() {
                @Override
                public void onRequestResult(JSONObject jobj) {
                    Log.d("server", jobj.toString());
                    Intent sendIntent = new Intent(ContentSubmitActivity.this, ContentDetailActivity.class);
                    try {
                        sendIntent.putExtra("myId", myId);
                        sendIntent.putExtra("postId", jobj.getString("hope_id"));
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                    startActivity(sendIntent);
                }
            });
        }
    };

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
