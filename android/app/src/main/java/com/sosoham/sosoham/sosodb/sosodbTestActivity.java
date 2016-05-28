package com.sosoham.sosoham.sosodb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sosoham.sosoham.R;

import org.json.JSONObject;

public class sosodbTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosodb_test);
        try {
            FaceBook.loginFB(new FaceBook.FaceBookListener() {
                @Override
                public void onAfterLogin() {

                }
            });
        }catch(Exception e){
            Log.d("onCreate", e.toString());
        }

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("my_id", "1");
            jsonObject.put("method","get_main_hope");
            Log.d("json", jsonObject.toString());
            SOSODB sosodb = new SOSODB();
            sosodb.add(jsonObject, new SOSODB.SOSODBListener() {
                @Override
                public void onRequestResult(JSONObject jobj) {

                }
            });
        }catch (Exception e){
            Log.d("JSON", e.toString());
        }
    }
}
