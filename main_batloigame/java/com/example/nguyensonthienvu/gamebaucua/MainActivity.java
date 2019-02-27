package com.example.nguyensonthienvu.gamebaucua;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    Custom_GridView_BanCo adapter;
    Integer[] dsHinh = {R.drawable.nai, R.drawable.bau, R.drawable.ga, R.drawable.ca, R.drawable.cua, R.drawable.tom};
    AnimationDrawable cdXiNgau1, cdXiNgau2, cdXiNgau3;
    ImageView hinhXiNgau1, hinhXiNgau2, hinhXiNgau3;
    Random randomXiNgau;
    int giaTriXiNgau1, giaTriXiNgau2, giaTriXiNgau3;
    public static Integer[] gtDatCuoc = new Integer[6];
    int tongtiencu,tongtienmoi;
    TextView tvTien;
    Timer timer = new Timer();
    Handler handler;
    int tienThuong, kiemtra;
    SharedPreferences luuTru;
    Callback callback = new Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            RandomXiNgau1();
            RandomXiNgau2();
            RandomXiNgau3();
            for (int i = 0;i < gtDatCuoc.length; i++){
                if (gtDatCuoc[i] != 0){
                    if (i == giaTriXiNgau1){
                        tienThuong += gtDatCuoc[i];
                    }
                    if (i == giaTriXiNgau2){
                        tienThuong += gtDatCuoc[i];
                    }
                    if (i == giaTriXiNgau3){
                        tienThuong += gtDatCuoc[i];
                    }
                    if (i != giaTriXiNgau1 && i != giaTriXiNgau2 && i != giaTriXiNgau3){
                        tienThuong -= gtDatCuoc[i];
                    }
                }
            }
            if (tienThuong > 0){
                Toast.makeText(getApplicationContext(),"hay" + tienThuong,Toast.LENGTH_SHORT).show();
            }
            else if(tienThuong == 0) {
                Toast.makeText(getApplicationContext(),"may qua",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"uc ngon"+tienThuong,Toast.LENGTH_SHORT).show();
            }
            LuuDuLieuNguoiDung(tienThuong);
            tvTien.setText(String.valueOf(tongtienmoi));
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hinhXiNgau1 = (ImageView) findViewById(R.id.xingau1);
        hinhXiNgau2 = (ImageView) findViewById(R.id.xingau2);
        hinhXiNgau3 = (ImageView) findViewById(R.id.xingau3);

        gridView = (GridView) findViewById(R.id.gvBanCo);
        adapter = new Custom_GridView_BanCo(this, R.layout.custom_banco, dsHinh);
        gridView.setAdapter(adapter);
        luuTru = getSharedPreferences("luutruthongtin", Context.MODE_PRIVATE);
        tongtiencu = luuTru.getInt("TongTien",1000);
        tvTien.setText(String.valueOf(tongtiencu));
        handler = new Handler(callback);
    }
    private void LuuDuLieuNguoiDung(int tienThuong){
        SharedPreferences.Editor edit = luuTru.edit();
        tongtienmoi = tongtiencu + tienThuong;
        edit.putInt("TongTien", tongtienmoi);
        edit.commit();
    }

    public void LacXiNgau(View view) {

        // Gan lai cho thich hop animation list
        hinhXiNgau1.setImageResource(R.drawable.hinhdongxigau);
        hinhXiNgau2.setImageResource(R.drawable.hinhdongxigau);
        hinhXiNgau3.setImageResource(R.drawable.hinhdongxigau);

        cdXiNgau1 = (AnimationDrawable) hinhXiNgau1.getDrawable();
        cdXiNgau2 = (AnimationDrawable) hinhXiNgau2.getDrawable();
        cdXiNgau3 = (AnimationDrawable) hinhXiNgau3.getDrawable();
        kiemtra = 0;
        for (int i = 0;i < gtDatCuoc.length; i++){
            kiemtra += gtDatCuoc[i];
        }
        if (kiemtra == 0){
            Toast.makeText(getApplicationContext(),"Ban vui long dat cuoc!",Toast.LENGTH_SHORT).show();
        }
        else {
            if (kiemtra > tongtiencu){
                Toast.makeText(getApplicationContext(),"Ban vui long dat cuoc ko vuot qua so tien dang co!",Toast.LENGTH_SHORT).show();
            }else {
                cdXiNgau1.start();
                cdXiNgau2.start();
                cdXiNgau3.start();
                tienThuong=0;
                timer.schedule(new LacXiNgau(), 1000);
            }
        }
    }

    class LacXiNgau extends TimerTask {

        @Override
        public void run() {
        handler.sendEmptyMessage(0);
        }
    }

    private void RandomXiNgau1() {
        randomXiNgau = new Random();
        int rd = randomXiNgau.nextInt(6);
        switch (rd) {
            case 0:
                hinhXiNgau1.setImageResource(dsHinh[0]);
                giaTriXiNgau1 = rd;
                break;
            case 1:
                hinhXiNgau1.setImageResource(dsHinh[1]);
                giaTriXiNgau1 = rd;
                break;
            case 2:
                hinhXiNgau1.setImageResource(dsHinh[2]);
                giaTriXiNgau1 = rd;
                break;
            case 3:
                hinhXiNgau1.setImageResource(dsHinh[3]);
                giaTriXiNgau1 = rd;
                break;
            case 4:
                hinhXiNgau1.setImageResource(dsHinh[4]);
                giaTriXiNgau1 = rd;
                break;
            case 5:
                hinhXiNgau1.setImageResource(dsHinh[5]);
                giaTriXiNgau1 = rd;
                break;
        }
    }
    private void RandomXiNgau2() {
        randomXiNgau = new Random();
        int rd = randomXiNgau.nextInt(6);
        switch (rd) {
            case 0:
                hinhXiNgau2.setImageResource(dsHinh[0]);
                giaTriXiNgau2 = rd;
                break;
            case 1:
                hinhXiNgau2.setImageResource(dsHinh[1]);
                giaTriXiNgau2 = rd;
                break;
            case 2:
                hinhXiNgau2.setImageResource(dsHinh[2]);
                giaTriXiNgau2 = rd;
                break;
            case 3:
                hinhXiNgau2.setImageResource(dsHinh[3]);
                giaTriXiNgau2 = rd;
                break;
            case 4:
                hinhXiNgau2.setImageResource(dsHinh[4]);
                giaTriXiNgau2 = rd;
                break;
            case 5:
                hinhXiNgau2.setImageResource(dsHinh[5]);
                giaTriXiNgau2 = rd;
                break;
        }
    }
    private void RandomXiNgau3() {
        randomXiNgau = new Random();
        int rd = randomXiNgau.nextInt(6);
        switch (rd) {
            case 0:
                hinhXiNgau3.setImageResource(dsHinh[0]);
                giaTriXiNgau3 = rd;
                break;
            case 1:
                hinhXiNgau3.setImageResource(dsHinh[1]);
                giaTriXiNgau3 = rd;
                break;
            case 2:
                hinhXiNgau3.setImageResource(dsHinh[2]);
                giaTriXiNgau3 = rd;
                break;
            case 3:
                hinhXiNgau3.setImageResource(dsHinh[3]);
                giaTriXiNgau3 = rd;
                break;
            case 4:
                hinhXiNgau3.setImageResource(dsHinh[4]);
                giaTriXiNgau3 = rd;
                break;
            case 5:
                hinhXiNgau3.setImageResource(dsHinh[5]);
                giaTriXiNgau3 = rd;
                break;
        }
    }
}
