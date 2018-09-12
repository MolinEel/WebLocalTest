package com.molin.lee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.molin.lee.sensortest.SonserTestActivity;
import com.molin.lee.weblocaltest.WebAndLocalCorrespondActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toWebAndLocalCorrespond(View v) {
        Intent intent = new Intent(this, WebAndLocalCorrespondActivity.class);
        startActivity(intent);
    }

    public void toSonserTest(View v){
        Intent intent = new Intent(this, SonserTestActivity.class);
        startActivity(intent);
    }
}
