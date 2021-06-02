package com.example.darts;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    ArrayList<Statistics> arrayList = new ArrayList<>();
    MyAdapter2 myAdapter2;
    DBhelperSecond DB2;
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        l1 =(ListView)findViewById(R.id.ListView_Stats);
        DB2 = new DBhelperSecond(this);
        LoaddataListView();

    }
    public void LoaddataListView(){
        arrayList = DB2.getAllData();
        myAdapter2 = new MyAdapter2(this,arrayList);
        l1.setAdapter(myAdapter2);
        myAdapter2.notifyDataSetChanged();
    }

}