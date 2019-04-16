package com.example.baitapcaithien.drawingfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    Button btnExe2, btnExe3, btnExe4, btnExe5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnExe2 = (Button)findViewById(R.id.btnExe2);
        btnExe3 = (Button)findViewById(R.id.btnExe3);
        btnExe4 = (Button)findViewById(R.id.btnExe4);
        btnExe5 = (Button)findViewById(R.id.btnExe5);
        btnExe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        btnExe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MapActivity.class);
                startActivity(intent);

            }
        });
        btnExe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,MainActivityAnimation.class);
                startActivity(intent);

            }
        });
        btnExe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,MainActivityExe4.class);
                startActivity(intent);

            }
        });
    }

}
