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

import org.json.JSONArray;
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
        SOSODB sosodb = new SOSODB();
        final JSONObject Jobject = new JSONObject( );

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
                    JSONArray hope_list = jobj.getJSONArray("hope_list");
                    for(int i=0; i<hope_list.length();i++){
                            JSONObject hope = hope_list.getJSONObject(i);
                            uid = Integer.toString(hope.getInt("hope_id"));
                            ucontent = hope.getString("gift_content");
                            alist.add(new HopeVo(uid, "", "", "", "", "", "", "쏀치한 오늘 ㅠㅠ", ucontent));
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        adapter = new MyCustomListView(this,alist);
        flingContainer.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

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
