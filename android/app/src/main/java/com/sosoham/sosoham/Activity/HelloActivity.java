package com.sosoham.sosoham.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.sosoham.sosoham.Adapter.MyCustomListView;
import com.sosoham.sosoham.R;
import com.sosoham.sosoham.Vo.HopeVo;
import com.sosoham.sosoham.sosodb.SOSODB;

import org.json.JSONObject;

import java.util.ArrayList;

public class HelloActivity extends Activity {

    SwipeFlingAdapterView flingContainer;
    MyCustomListView adapter;


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


        final ArrayList<HopeVo> alist = new ArrayList<HopeVo>();
        HopeVo a = new HopeVo("a","a","a","a","a","a","a","쏀치한 오늘 ㅠㅠ","오늘 저녁은 치킨 먹고 싶어요!!");
        HopeVo b = new HopeVo("b","b","b","b","b","b","b","b","b");
        alist.add(a);
        alist.add(b);
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


        SOSODB sosodb = new SOSODB();
        sosodb.get(new JSONObject(), new SOSODB.SOSODBListener() {
            @Override
            public void onRequestResult(JSONObject jobj) {

            }
        });






    }
}
