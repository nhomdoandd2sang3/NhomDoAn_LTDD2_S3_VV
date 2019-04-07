package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainSnakeActivity extends AppCompatActivity {

    Button btnplay,btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_main);

        btnplay = (Button) findViewById(R.id.btnPlay);
        btnback = (Button) findViewById(R.id.btnBack);

        btnplay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainSnakeActivity.this, SnakeActivity.class);
                startActivity(intent);
            }
        });


    /*    btnback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,    .class);
                startActivity(intent);
            }
        });

*/
    }
}
