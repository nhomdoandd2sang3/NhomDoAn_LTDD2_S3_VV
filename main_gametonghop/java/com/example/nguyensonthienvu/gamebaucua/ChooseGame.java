package com.example.nguyensonthienvu.gamebaucua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseGame extends AppCompatActivity {
    Button btnRan,btnXepHinh,btnPikachu,btnKukube,btnBauCua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        btnRan = (Button) findViewById(R.id.btnRan);
        btnXepHinh=(Button) findViewById(R.id.btnXepHinh);
        btnPikachu=(Button)findViewById(R.id.btnPikachu);
        btnKukube=(Button)findViewById(R.id.btnKukube);
        btnBauCua=(Button)findViewById(R.id.btnBauCua);
        btnKukube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this,ManChoi.class);
                startActivity(intent);
            }
        });
        btnPikachu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this,Pikachu.class);
                startActivity(intent);
            }
        });
        btnRan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this, MainSnakeActivity.class);
                startActivity(intent);
            }
        });
        btnBauCua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
