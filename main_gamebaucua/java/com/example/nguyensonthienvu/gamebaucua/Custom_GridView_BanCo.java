package com.example.nguyensonthienvu.gamebaucua;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;

public class Custom_GridView_BanCo extends ArrayAdapter<Integer> {

    Context context;
    int resource;
    Integer[] objects;
    Integer[] giaTien = {0, 100, 200, 300, 400, 500};
    ArrayAdapter<Integer> adapter;
    public Custom_GridView_BanCo(@NonNull Context context, int resource, @NonNull Integer[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        adapter = new ArrayAdapter<Integer>(context,android.R.layout.simple_spinner_item, giaTien);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View view = View.inflate(context, resource, null);
        ImageView imgBanCo = (ImageView)view.findViewById(R.id.imgBanCo);
        Spinner spinnerGiaTien = (Spinner) view.findViewById(R.id.spinnerGiaTien);
        imgBanCo.setImageResource(objects[position]);
        spinnerGiaTien.setAdapter(adapter);
        spinnerGiaTien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positionspinner, long id) {
                MainActivity.gtDatCuoc[position] = giaTien[positionspinner];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ///////////
        RecyclerView mRecyclerView;
        RecyclerViewAdapter mRcvAdapter;
        final List<String> data;
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        data = new ArrayList<>();
        data.add("0");
        data.add("100");
        data.add("200");
        data.add("300");
        data.add("400");
        data.add("500");

        mRcvAdapter = new RecyclerViewAdapter(view.getContext(), data);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRcvAdapter);

        mRcvAdapter.setOnItemClickedListener(new RecyclerViewAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(String username, int vt) {
                MainActivity.gtDatCuoc[position] = giaTien[vt];

                Toast.makeText(view.getContext(), username, Toast.LENGTH_SHORT).show();
            }
        });


        ///////////////////////////////////////////////////


        return view;
    }
}
