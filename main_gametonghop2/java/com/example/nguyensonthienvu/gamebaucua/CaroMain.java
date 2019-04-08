package com.example.nguyensonthienvu.gamebaucua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;

public class CaroMain extends Activity {

    private  MyView myChess;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_caro);
        myChess = (MyView)findViewById(R.id.myView1);

    }



}
