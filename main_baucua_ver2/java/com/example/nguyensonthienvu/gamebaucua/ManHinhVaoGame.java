package com.example.nguyensonthienvu.gamebaucua;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManHinhVaoGame extends AppCompatActivity {
    Button btnVaoGame;
    Button btnThoat;
    MediaPlayer nhacnen=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_vao_game);
        setControl();
        setEvent();
        nhacnen=MediaPlayer.create(this,R.raw.nhacnennguoinhen);
        nhacnen.start();
    }
    public void setControl(){
        btnVaoGame=(Button) findViewById(R.id.btnVaoGame);
        btnThoat=(Button) findViewById(R.id.btnThoat);
    }
    public void setEvent(){
        btnVaoGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhVaoGame.this,MainActivity.class);
                startActivity(intent);
                nhacnen.stop();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
