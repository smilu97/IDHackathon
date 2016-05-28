package com.sosoham.sosoham;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sosoham.sosoham.sosodb.FaceBook;
import com.sosoham.sosoham.sosodb.SOSODB;

import org.json.JSONObject;

/**
 * Created by mac on 2016. 5. 29..
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosodb_test);
        try {
            FaceBook.loginFB(new FaceBook.FaceBookListener() {
                @Override
                public void onAfterLogin() {
                    Intent intent=new Intent(MainActivity.this,HelloActivity.class);
                    startActivity(intent);
                }
            });
        }catch(Exception e){
            Log.d("onCreate", e.toString());
        }
    }
}
