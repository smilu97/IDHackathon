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
    }
}
