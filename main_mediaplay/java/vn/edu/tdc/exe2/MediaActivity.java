package vn.edu.tdc.exe2;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MediaActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SeekBar seekBar2;
    TextView batdau;
    TextView ketthuc;
    ImageButton imvPlay, imvPause, imvNext, imvPrev;
    private double thoigianbatdau=0;
    private double thoigianketthuc=0;
    private static int loadSeekBar=0;
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.b1);
        batdau = (TextView) findViewById(R.id.batdau);
        ketthuc =(TextView) findViewById(R.id.ketthuc);
        seekBar2 =(SeekBar) findViewById(R.id.seekBar2);
//        imvNext = (ImageButton) findViewById(R.id.imvNext);
//        imvPrev = (ImageButton) findViewById(R.id.imvPrev);
//
//        imvPause = (ImageButton) findViewById(R.id.imvPause);
//        imvPlay = (ImageButton) findViewById(R.id.imvPlay);
//
//        imvPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            mediaPlayer.start();
//            Toast.makeText(MediaActivity.this, "play", Toast.LENGTH_SHORT).show();
//            }
//        });
//        imvPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                Toast.makeText(MediaActivity.this, "pause", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    public void Play(View view){
    mediaPlayer.start();
    thoigianbatdau = mediaPlayer.getCurrentPosition();
    thoigianketthuc = mediaPlayer.getDuration();
    if(loadSeekBar == 0){
    seekBar2.setMax((int)thoigianketthuc);
    }
    seekBar2.setProgress((int)thoigianbatdau);
    chuyenthoigian();
    myHandler.postDelayed(Capnhatthoigian, 100);
    }
    public void chuyenthoigian() {
        long phutbatdau = TimeUnit.MILLISECONDS.toMinutes((long)thoigianbatdau);
        long giaybatdau = TimeUnit.MILLISECONDS.toSeconds((long)thoigianbatdau) - TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MINUTES.toSeconds(phutbatdau));
        batdau.setText(String.format("%d phut $d giay",phutbatdau,giaybatdau));
        long phutketthuc = TimeUnit.MILLISECONDS.toMinutes((long)thoigianketthuc);
        long giayketthuc = TimeUnit.MILLISECONDS.toSeconds((long)thoigianketthuc)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)thoigianketthuc));
        ketthuc.setText(String.format("%d phut $d giay",phutketthuc,giayketthuc));
    }
    private Runnable Capnhatthoigian = new Runnable() {
        @Override
        public void run() {
            thoigianbatdau = mediaPlayer.getCurrentPosition();
            long phutbatdau = TimeUnit.MILLISECONDS.toMinutes((long)thoigianbatdau);
            long giaybatdau = TimeUnit.MILLISECONDS.toSeconds((long)thoigianbatdau) - TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MINUTES.toSeconds(phutbatdau));
            batdau.setText(String.format("%d phut $d giay",phutbatdau,giaybatdau));
            myHandler.postDelayed(this,100);
            seekBar2.setProgress((int)thoigianbatdau);
        }
    };
    public void Pause(View view) {
        mediaPlayer.pause();
        Toast.makeText(getApplicationContext(),"pause",Toast.LENGTH_SHORT).show();
        imvPause.setEnabled(false);
        imvPlay.setEnabled(true);
    }
    public void Next(View view){
        double thoigianDB = thoigianbatdau+5000;
        if(thoigianDB <= thoigianketthuc){
            mediaPlayer.seekTo((int) thoigianDB);
        }
            else{
            Toast.makeText(getApplicationContext(),"ko the luiot dc nua",Toast.LENGTH_SHORT).show();
        }

    }
    public void Prev(View view) {
        double thoigianDB = thoigianbatdau - 5000;
        if(thoigianDB>=0){
            mediaPlayer.seekTo((int)thoigianDB);

        }else{
            Toast.makeText(getApplicationContext(),"ko the lui dc nua",Toast.LENGTH_SHORT).show();
        }
    }

}
