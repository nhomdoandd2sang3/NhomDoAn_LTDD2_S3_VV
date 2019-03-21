package com.example.gamekukube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //chuyen qua man hinh man choi
        Intent intent=new Intent(MainActivity.this,ManChoi.class);
        startActivity(intent);
    }
}
