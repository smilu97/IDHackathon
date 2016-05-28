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
            JSONObject jobj = new JSONObject("{\"test_key\":123}");
            HttpRequestJSON hrj = new HttpRequestJSON();
            hrj.sendPost("test", jobj, new HttpRequestJSON.HttpRequestJSONListener() {
                @Override
                public void onRequestResult(JSONObject jsonObject) {
                    Log.d("onResult", jsonObject.toString());
                }
            });
        }catch(Exception e){
            Log.d("onCreate", e.toString());
        }
    }
}
