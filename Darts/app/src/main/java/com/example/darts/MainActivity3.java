package com.example.darts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.darts.R;

import java.io.File;

public class MainActivity3 extends AppCompatActivity {
    TextView textName;
    private static final String DB_PATH = "data/data/com.example.darts2/databases/Userdata.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textName= (TextView)findViewById(R.id.textName);
        textName.setText(Names.getWinnername());
    }
    public  void Click1(View v){
            Intent intent = new Intent(this, MainActivity.class);
            doDBCheck();
            startActivity(intent);
    }
    public  void Click2(View v){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }
    private void doDBCheck()
    {
        try{
            File file = new File(DB_PATH);
            file.delete();
        }catch(Exception ex)
        {}
    }

}