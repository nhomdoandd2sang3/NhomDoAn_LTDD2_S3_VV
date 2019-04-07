package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class ManChoi extends AppCompatActivity {

    TextView tvThoigian,tvDiem;
    GridView gvMau;
    CongCu congCu = new CongCu();
    ArrayList arr;
    int diem,soO,kiemtra=0;

//    CountDownTimer timer1=new CountDownTimer(3000,10) {
//
//        public void onTick(long millisUntilFinished) {
//            tvThoigian.setText(millisUntilFinished / 10 + "");
//        }
//
//        public void onFinish() {
//            kiemtra=1;
//            tvThoigian.setText("0");
//        }
//    };
    //copy tren mang thoi gian dem nguoc
    CountDownTimer timer=new CountDownTimer(3000,10) {

        public void onTick(long millisUntilFinished) {
            tvThoigian.setText(millisUntilFinished / 10 + "");
        }

        public void onFinish() {
//            kiemtra=1;
            tvThoigian.setText("0");
            AlertDialog.Builder newDialog = new AlertDialog.Builder(ManChoi.this);
            newDialog.setTitle("Thong bao");
            newDialog.setMessage("Ban có muốn tiếp tục chơi game này ?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    timer.start();

                    dialog.cancel();
                }
            });
            newDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(ManChoi.this,ChooseGame.class);
                    startActivity(intent);

                }
            });
            newDialog.show();
        }
    }.start();

    //

    @Override
    //ket noi vs xml man choi
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_manchoi);
        anhxa();
        caidatGridView();
        setDuLieu();
        setSuKien();
    }
    //tao ánh xạ
    private void anhxa(){
        gvMau=(GridView) findViewById(R.id.gvMau);
        tvDiem=(TextView)findViewById(R.id.tvDiem) ;
        tvThoigian=(TextView)findViewById(R.id.tvThoigian);
        diem=0;
    }
    private void caidatGridView(){
        if(diem<10){
            gvMau.setNumColumns(2);//neu diem nho hon 10 so cot la 2 so o la 4
            soO=4;
        }
        else {//neu diem >10  thi so o se tang len
            gvMau.setNumColumns(diem/10+2);
            soO=(diem/10+2)*(diem/10+2);
        }

    }
    private void setDuLieu(){
        arr=new ArrayList(congCu.taoMau(soO));
        Adapter adapter=new Adapter(ManChoi.this,R.layout.layout_manchoi,arr);
        gvMau.setAdapter(adapter);
        tvDiem.setText(Integer.toString(diem));
    }
    //set su kien click chuyen mau khac
    private void setSuKien(){
        gvMau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==congCu.DapAn){
                    caidatGridView();//goi no vo day 1 lan de no co the chay set level vi ham nay chi chay 1 lan
                    timer.start(); //neu dung thi thoi gian dc cong them
                    diem++; //neu dung diem se cong len 1
                    setDuLieu();//position so thu tu
                }

            }
        });
    }
}
