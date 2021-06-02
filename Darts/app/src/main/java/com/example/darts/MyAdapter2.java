package com.example.darts;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter2 extends BaseAdapter {

    Context context;
    ArrayList<Statistics> arrayList;
    public MyAdapter2(Context context,ArrayList<Statistics> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.mycustomlistview2,null);
        View view = convertView;
        TextView t1_name = (TextView)convertView.findViewById(R.id.nev);
        TextView t2_allpoints = (TextView)convertView.findViewById(R.id.korszam);
        TextView t3_ot = (TextView)convertView.findViewById(R.id.gyozelem);
        TextView t4_hat = (TextView)convertView.findViewById(R.id.kisebbbuntetopont);
        TextView t5_het = (TextView)convertView.findViewById(R.id.nagyobbbuntetopont);
        TextView t6_nyolc = (TextView)convertView.findViewById(R.id.jatekszam);

        Statistics statistics = arrayList.get(position);
        t1_name.setText(statistics.getName());
        t2_allpoints.setText(String.valueOf(statistics.getKorszam()));
        t3_ot.setText(String.valueOf(statistics.getGyozelem()));
        t4_hat.setText(String.valueOf(statistics.getKisebbbuntetopont()));
        t5_het.setText(String.valueOf(statistics.getNagyobbbuntetopont()));
        t6_nyolc.setText(String.valueOf(statistics.getJatekszam()));

        return convertView;
    }

}
