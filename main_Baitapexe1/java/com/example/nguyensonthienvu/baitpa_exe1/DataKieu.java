package com.example.nguyensonthienvu.baitpa_exe1;

import android.app.Activity;
import android.content.Intent;

public class DataKieu {
    private String Kieu;

    public DataKieu(String kieu){
        Kieu = kieu;
    }

    public String getKieu() {
        return Kieu;
    }

    public void setKieu(String kieu) {
        Kieu = kieu;
    }
//    public void changeText(String kieu){
//        Kieu = kieu;
//    }

}
