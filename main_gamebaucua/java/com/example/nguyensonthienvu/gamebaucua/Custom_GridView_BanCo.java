package com.example.nguyensonthienvu.gamebaucua;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Custom_GridView_BanCo extends ArrayAdapter<Integer> {
    Context context;
    int resource;
    Integer[] objects;
    Integer[] giatien = {0, 100, 200, 300, 400, 500};
    ArrayAdapter<Integer> adapter;
    public Custom_GridView_BanCo(@NonNull Context context, int resource, @NonNull Integer[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        adapter = new ArrayAdapter<Integer>(context,android.R.layout.simple_spinner_item, giatien);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = View.inflate(context, resource, null);
        ImageView imgBanCo = (ImageView)view.findViewById(R.id.imgBanCo);
        Spinner spinnerGiaTien = (Spinner) view.findViewById(R.id.spinnerGiaTien);
        imgBanCo.setImageResource(objects[position]);
        spinnerGiaTien.setAdapter(adapter);
        return view;
    }
}
