package com.example.darts;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.darts.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Players> arrayList;
    public MyAdapter(Context context,ArrayList<Players> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mycustomlistview,null);
            View view = convertView;
            TextView t1_name = (TextView)convertView.findViewById(R.id.nameee);
            TextView t2_allpoints = (TextView)convertView.findViewById(R.id.allpoints);
            TextView t3_ot = (TextView)convertView.findViewById(R.id.ot);
            TextView t4_hat = (TextView)convertView.findViewById(R.id.hat);
            TextView t5_het = (TextView)convertView.findViewById(R.id.het);
            TextView t6_nyolc = (TextView)convertView.findViewById(R.id.nyolc);
            TextView t7_kilenc = (TextView)convertView.findViewById(R.id.kilenc);
            TextView t8_husz = (TextView)convertView.findViewById(R.id.husz);
            TextView t9_huszonot = (TextView)convertView.findViewById(R.id.huszonot);

            Players players = arrayList.get(position);
            t1_name.setText(players.getName());
            t2_allpoints.setText(String.valueOf(players.getAllpoint()));
            t3_ot.setText(String.valueOf(players.getOt()));
            t4_hat.setText(String.valueOf(players.getHat()));
            t5_het.setText(String.valueOf(players.getHet()));
            t6_nyolc.setText(String.valueOf(players.getNyolc()));
            t7_kilenc.setText(String.valueOf(players.getKilenc()));
            t8_husz.setText(String.valueOf(players.getHusz()));
            t9_huszonot.setText(String.valueOf(players.getHuszonot()));

            if(players.getOt() >= 3){t3_ot.setTextColor(Color.GREEN);}
            if(players.getHat() >= 3){t4_hat.setTextColor(Color.GREEN);}
            if(players.getHet() >= 3){t5_het.setTextColor(Color.GREEN);}
            if(players.getNyolc() >= 3){t6_nyolc.setTextColor(Color.GREEN);}
            if(players.getKilenc() >= 3){t7_kilenc.setTextColor(Color.GREEN);}
            if(players.getHusz() >= 3){t8_husz.setTextColor(Color.GREEN);}
            if(players.getHuszonot() >= 3){t9_huszonot.setTextColor(Color.GREEN);}


        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
