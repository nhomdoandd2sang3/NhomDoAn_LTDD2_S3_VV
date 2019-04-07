package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoseActivity extends AppCompatActivity {

    Button btnplayagain,btnmenu;
    TextView txtscore,txthighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose);

        txtscore = (TextView) findViewById(R.id.txtScore);
        txthighscore = (TextView) findViewById(R.id.txtHighScore);
        btnplayagain = (Button) findViewById(R.id.btnPlayAgain);
        btnmenu = (Button) findViewById(R.id.btnMenu);

        Bundle extras = getIntent().getExtras();
        txtscore.setText(extras.getString("score"));

        int player_score = Integer.valueOf(txtscore.getText().toString());

        SharedPreferences setting = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int high_score = setting.getInt("HIGH_SCORE",0);

        if (player_score > high_score){
            txthighscore.setText("Điểm cao nhất: " + player_score);

            SharedPreferences.Editor editor = setting.edit();
            editor.putInt("HIGH_SCORE",player_score);
            editor.commit();
            Toast.makeText(LoseActivity.this,
                    "Phá được kỷ lục điểm cao nhất", Toast.LENGTH_SHORT).show();
        }
        else
        {
            txthighscore.setText("Điểm cao nhất: " + high_score);
            Toast.makeText(LoseActivity.this,
                    "Chưa phá được kỷ lục", Toast.LENGTH_SHORT).show();
        }

        btnplayagain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent_new = new Intent(LoseActivity.this, SnakeActivity.class);
                startActivity(intent_new);
            }
        });

        btnmenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent_new = new Intent(LoseActivity.this, MainSnakeActivity.class);
                startActivity(intent_new);
            }
        });

    }
}
