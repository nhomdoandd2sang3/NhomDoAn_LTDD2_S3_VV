package com.example.myapplication;

import java.util.ArrayList;
import java.util.Random;
//tao mau random
public class CongCu {
    static  int DapAn;
    Random r=new Random();
    String string[]=new String[]{"#F71818","#FC5E5E","#DC11C1","#9C1089","#4E1199","#790DFC","#195D78","#0C455B","#097561","#1C7A69","#0E673A","#0D5230","#25550E","#46802B","#A1A407","#73750E","#F99F05","#F6AB2A"};
    public ArrayList<String> taoMau(int n){
        int x,y;
        ArrayList<String> a=new ArrayList<>();
        x=r.nextInt(n);//0 - n-1
        DapAn=x;
        y=r.nextInt(17);
        for (int i=0;i<n;i++){
            if(i==x){
                a.add(string[y]);
            }
            else {
                a.add(string[y+1]);
            }
        }
        return a;
    }
}
