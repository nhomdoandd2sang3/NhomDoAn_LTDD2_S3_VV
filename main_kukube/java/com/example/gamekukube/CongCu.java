package com.example.gamekukube;

import java.util.ArrayList;
import java.util.Random;
//tao mau random
public class CongCu {
    static  int DapAn;
    Random r=new Random();
    String string[]=new String[]{"#F71818","#FA9B9B","#DC11C1","#7A0C6B","#4E1199","#790DFC","#195D78","#16B4F2","#097561","#13F5CB","#0E673A","#1AF286","#25550E","#8CF35C","#A1A407","#F3F550","#F99F05","#F7C672"};
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
