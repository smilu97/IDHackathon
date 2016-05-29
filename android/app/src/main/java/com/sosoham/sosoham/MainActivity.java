package com.sosoham.sosoham;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sosoham.sosoham.Activity.HelloActivity;
import com.sosoham.sosoham.sosodb.FaceBook;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
