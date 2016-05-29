package com.sosoham.sosoham;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sosoham.sosoham.sosodb.SOSODB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContentDetailActivity extends AppCompatActivity {
    public ArrayList<CommentListItem> commentDat;
    public String postId;
    public String myId;
    public SOSODB sosodb = new SOSODB();

    protected void UpdateList() {
        JSONObject postJson = new JSONObject();
        try {
            postJson.put("my_id", myId);
            postJson.put("method", "get_detail_hope");
            postJson.put("target_id", myId);
            postJson.put("hope_id", postId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sosodb.get(postJson, new SOSODB.SOSODBListener() {
            @Override
            public void onRequestResult(JSONObject jobj) {
                try {
                    TextView contentText = (TextView)findViewById(R.id.detailContextText);
                    TextView titleText = (TextView)findViewById(R.id.detailTitleText);
                    JSONObject hope = jobj.getJSONArray("hope_list").getJSONObject(0);
                    contentText.setText(hope.getString("gift_content"));
                    titleText.setText(hope.getString("gift_name"));
                    JSONArray actionList = hope.getJSONArray("action_list");
                    for(int i=0;i<actionList.length(); ++i)
                    {
                        try {
                            JSONObject order = actionList.getJSONObject(i);
                            Log.d("action", order.getString("action_whose_name"));
                            String actionProfileUrl = order.getString("action_profile_url");
                            String actionName = order.getString("action_whose_name");
                            int actionType = order.getInt("action_type");
                            CommentListItem cli = new CommentListItem(actionProfileUrl, actionName, actionType);
                            commentDat.add(cli);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch(JSONException e) {
                    e.printStackTrace();
                }
                CommentListAdapter commentAdapter = new CommentListAdapter(ContentDetailActivity.this, R.layout.comment, commentDat);
                ListView commentList = (ListView)findViewById(R.id.CommentListView);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);
        Intent intent = getIntent();
        myId = intent.getStringExtra("myId");
        ImageView aButton = (ImageView)findViewById(R.id.detailActionAButton);
        ImageView bButton = (ImageView)findViewById(R.id.detailActionBButton);
        ImageView purButton = (ImageView)findViewById(R.id.detailPurchaseButton);
        aButton.setOnClickListener(buttonListener);
        bButton.setOnClickListener(buttonListener);
        purButton.setOnClickListener(buttonListener);
        postId = intent.getStringExtra("postId");
        UpdateList();
    }
    Button.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick(View v) {
            int actionType = -1;
            switch(v.getId()) {
                case R.id.detailActionAButton :
                    actionType = 1;
                    break;
                case R.id.detailActionBButton :
                    actionType = 2;
                    break;
                case R.id.detailPurchaseButton :
                    Intent tointent = new Intent(ContentDetailActivity.this, PurchaseActivity.class);
                    startActivity(tointent);
                    break;
            }
            if (actionType != -1) {
                JSONObject sobj = new JSONObject();
                try {
                    sobj.put("my_id", myId);
                    sobj.put("method", "give_action");
                    sobj.put("hope_id", postId);
                    sobj.put("action_type", Integer.toString(actionType));
                }catch (JSONException e){
                    e.printStackTrace();
                }
                sosodb.give(sobj, new SOSODB.SOSODBListener() {
                    @Override
                    public void onRequestResult(JSONObject jobj) {
                        UpdateList();
                    }
                });
            }
        }
    };
}
