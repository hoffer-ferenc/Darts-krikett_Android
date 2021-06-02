package com.example.darts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.darts.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button insert;
    EditText name1, name2, name3;
    DBhelper DB;
    private static final String DB_PATH = "data/data/com.example.darts2/databases/Userdata.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doDBCheck();
        setContentView(R.layout.activity_main);
        insert = findViewById(R.id.btnInsert);
        name1 = findViewById(R.id.nameone);
        name2 = findViewById(R.id.nametwo);
        name3 = findViewById(R.id.namethree);
        DB = new DBhelper(this);

        insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Players valaki = new Players();
                String nameTXT = name1.getText().toString();
                String nameTXT2 = name2.getText().toString();
                String nameTXT3 = name3.getText().toString();

               DB.insertuserdata(1,nameTXT2, 0, 0,0,0,0,0,0,0);
               DB.insertuserdata(2,nameTXT3, 0, 0,0,0,0,0,0,0);


                Boolean checkinsertdata = DB.insertuserdata(0,nameTXT, 0, 0,0,0,0,0,0,0);;
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "Új nevek hozzáadva", Toast.LENGTH_SHORT).show();
                    Names valaki1 = new Names();
                    valaki1.setName1(nameTXT);
                    valaki1.setName2(nameTXT2);
                    valaki1.setName3(nameTXT3);

                    //Log.d("Names2", valaki.getName1()+valaki.getName2()+valaki.getName3());
                    openActivity2();}
                else
                    Toast.makeText(MainActivity.this, "Sikertelen", Toast.LENGTH_SHORT).show();
            }        });

    }
    public void openActivity2() {
        Intent intent = new Intent(this, Main2.class);
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