package com.yzd.help;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void one(View view) {
        startActivity(new Intent(this, OneListActivity.class));
    }

    public void two(View view) {
        startActivity(new Intent(this, AnalyzeActivity.class));
    }
}
