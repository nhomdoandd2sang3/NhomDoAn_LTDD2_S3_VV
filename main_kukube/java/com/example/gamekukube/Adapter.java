package com.example.gamekukube;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<String> {
    Context ct;
    ArrayList<String> arr;
    public Adapter( Context context, int resource, List objects) {
        super(context, resource, objects);
        this.ct=context;
        this.arr=new ArrayList<>(objects);
    }

    //dung de chuyen doi string thanh mau
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View row=convertView;
        LayoutInflater inflater=(LayoutInflater)ct.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
        //cho adepter b iet do ma mau string vao layout item
        row=inflater.inflate(R.layout.item,null);
        if(arr!=null&&arr.size()>0){
            com.example.gamekukube.textView a=(com.example.gamekukube.textView)row.findViewById(R.id.item);
            a.setBackgroundColor(Color.parseColor(arr.get(position)));
        }
        //return super.getView(position, convertView, parent);
        return row;
    }
}
