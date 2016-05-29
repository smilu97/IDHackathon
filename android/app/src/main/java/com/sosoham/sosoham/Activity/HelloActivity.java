package com.sosoham.sosoham.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.sosoham.sosoham.Adapter.MyCustomListView;
import com.sosoham.sosoham.R;
import com.sosoham.sosoham.Vo.HopeVo;
import com.sosoham.sosoham.sosodb.FaceBook;
import com.sosoham.sosoham.sosodb.SOSODB;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HelloActivity extends Activity {

    SwipeFlingAdapterView flingContainer;
    MyCustomListView adapter;
    ArrayList<HopeVo> alist = new ArrayList<HopeVo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
      //  writebtn=(android.support.design.widget.FloatingActionButton) findViewById(R.id.actionButton);
        //fab = (FloatingActionButton) findViewById(R.id.fab);

    /*    fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
               // Intent intent = new Intent(MainActivity.this, NewMessageActivity.class);
                //startActivity(intent);
            }
        });*/
        SOSODB sosodb = new SOSODB();
        JSONObject Jobject = new JSONObject( );

        try {
            Jobject.put("my_id", FaceBook.my_id);
            Jobject.put("method","get_main_hope");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sosodb.get(Jobject, new SOSODB.SOSODBListener() {
            String uid;
            String ucontent;

            @Override
            public void onRequestResult(JSONObject jobj) {
                try {

                    Log.d("result", jobj.toString());

                    for(int i=0; i<jobj.length();i++){
                        uid=jobj.getString("hope_id");
                        ucontent = jobj.getString("gift_content");
                        alist.add(new HopeVo(uid,"a","a","a","a","a","a","쏀치한 오늘 ㅠㅠ",ucontent));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        adapter = new MyCustomListView(this,alist);
        //writebtn.OnClickListener(
        flingContainer.setAdapter(adapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //flingContainer.focusSearch(View.FOCUS_LEFT);
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject


            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Log.i("??", dataObject.toString());
                //adapter.getView(1,null,this);

                //alist.remove(0);
                // flingContainer.
                // adapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //al.add("XML ".concat(String.valueOf(itemsInAdapter)));
                adapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                itemsInAdapter++;
            }

            @Override
            public void onScroll(float v) {
                Log.i("스크롤", "얍얍");
            }
        });









    }
}
