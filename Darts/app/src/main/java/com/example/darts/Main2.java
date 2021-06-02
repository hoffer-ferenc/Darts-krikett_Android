package com.example.darts;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.darts.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main2 extends AppCompatActivity {
    EditText name, contact, editTextName,dob ;
    Button update, delete, view, btn1, btn2, btn3, btn4, btn5, btn6, btn7,save, undo;
    DBhelper DB;
    DBhelperSecond DB2;
    TextView textName;
    ListView l1;
    Numbers numbers = new Numbers();
    ArrayList<Players> arrayList;
    MyAdapter myAdapter;
    iteratior iter = new iteratior();
    int counter1 = 0;
    int counter2 = 0;
    int counter3 = 0;
    int counter4 = 0;
    int counter5 = 0;
    int counter6 = 0;
    int counter7 = 0;
    int maxharom = 0;
    int szam1 = 0;
    int szam2 = 0;
    int szam3 = 0;
    int max = 0;
    int ot=0,hat=0,het=0,nyolc=0,kilenc=0,husz=0,huszonot=0;
    Names valaki = new Names();
    String[] getallnames = {valaki.getName1(),valaki.getName2() ,valaki.getName3()};
    List<Players> list;
    List<Statistics> list2;
    List<Players> list3;
    String maxname;
    ArrayList<String> ar = new ArrayList<String>();
    ArrayList<String> ar2 = new ArrayList<String>();
    Players players = new Players();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textName= (TextView)findViewById(R.id.textName);
        l1 =(ListView)findViewById(R.id.ListView);
        update = findViewById(R.id.btnUpdate);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBhelper(this);
        DB2 = new DBhelperSecond(this);
        arrayList = new ArrayList<>();
        LoaddataListView();
        textName.setText(getallnames[iter.getIter()]);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        undo = (Button) findViewById(R.id.undo);

        if(iter.getGombszamlalo() == 0) {
            undo.setEnabled(false);
        }
        if(iter.getGombszamlalo() <= 2){
            undo.setEnabled(true);
            iter.setGombszamlalo(0);
        }
        
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do {
                if (iter.getIter() == 0){Number0();}
                if (iter.getIter() == 1){Number1();}
                if (iter.getIter() == 2){Number2();}

                list = feltolt();
                players.setList(list);

                Boolean checkupdatedata = DB.updateuserdataujj(getallnames[iter.getIter()],iter.getIter());
                buntetopont();
                if(checkupdatedata==true) {
                    int szam = iter.getIter() + 1;
                    iter.setIter(szam);
                    if(iter.getIter() == 3)
                    {
                        iter.setKorszamlalo(iter.getKorszamlalo()+1);
                        iter.setIter(0);
                    }
                    Log.d("Korszamlalo",  Integer.toString(iter.getKorszamlalo()));
                    //Log.d("ot",  Integer.toString(z));
                    Toast.makeText(Main2.this, "Sikeres feltöltés!", Toast.LENGTH_SHORT).show();
                    jatekvege();
                    if(!undo.isEnabled()){
                    iter.setGombszamlalo(iter.getGombszamlalo()+1);
                    }
                    nullaz();
                    finish();
                    startActivity(getIntent());
                    break;
                }
                else
                    Toast.makeText(Main2.this, "Sikertelen!", Toast.LENGTH_SHORT).show();
                } while (szam1 == 7 && list.get(0).getAllpoint() < list.get(1).getAllpoint() && list.get(0).getAllpoint() < list.get(2).getAllpoint() || szam2 == 7 && list.get(1).getAllpoint() < list.get(0).getAllpoint() && list.get(1).getAllpoint() < list.get(2).getAllpoint() || szam3 == 7 && list.get(2).getAllpoint() < list.get(1).getAllpoint() && list.get(2).getAllpoint() < list.get(0).getAllpoint());
                //Log.d("jatekvege" , Integer.toString(szam1));
                //openActivity3();
                if(szam1 == 7 && list.get(0).getAllpoint() < list.get(1).getAllpoint() && list.get(0).getAllpoint() < list.get(2).getAllpoint() || szam2 == 7 && list.get(1).getAllpoint() < list.get(0).getAllpoint() && list.get(1).getAllpoint() < list.get(2).getAllpoint() || szam3 == 7 && list.get(2).getAllpoint() < list.get(1).getAllpoint() && list.get(2).getAllpoint() < list.get(0).getAllpoint()){

                    max();
                    if(max == szam1){maxname = list.get(0).getName();}
                    if(max == szam2){maxname = list.get(1).getName();}
                    if(max == szam3){maxname = list.get(2).getName();}
                    Names.setWinnername(maxname);
                    if(feltolt2() == null || feltolt2().isEmpty()){
                        statisztika();
                        list2 = feltolt2();
                    }else{list2 = feltolt2();statisztika();}


                    Log.d("STATS",  list2.get(0).toString());
                    openActivity3();
                }
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true){
                    Toast.makeText(Main2.this, "Sikeres törlés!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Main2.this, "Sikertelen törlés!" +
                            "", Toast.LENGTH_SHORT).show();
            }        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = DB.getdata();

                if(res.getCount()==0){
                    Toast.makeText(Main2.this, "Sikertelen!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nev :"+res.getString(0)+"\n");
                    buffer.append("kontakt :"+res.getString(1)+"\n");
                    buffer.append("DB :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Main2.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }

    public void buntetopont(){
        if(iter.getIter() == 0){
            number00();
        }
        if(iter.getIter() == 1){
            number11();
        }
        if(iter.getIter() == 2){
            number22();
        }
    }
    public void LoaddataListView(){
        arrayList = DB.getAllData();
        myAdapter =new MyAdapter(this,arrayList);
        l1.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    public List<Players> feltolt(){
        List<Players> Lista = DB.getAllData();
        Cursor res = DB.getdata();
        if(res.getCount()==0){
            Toast.makeText(Main2.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        return Lista;
    }
    public List<Statistics> feltolt2(){
        List<Statistics> Lista = DB2.getAllData();
        Cursor res = DB2.getdata();
        if(res.getCount()==0){
            Toast.makeText(Main2.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        return Lista;
    }
    public void number00(){
        Cursor res = DB.getdata0();
        while(res.moveToNext()){
            int allpoint = res.getInt(2);
            numbers.setAllpoint(0+allpoint);
            int ot = res.getInt(3);
            //ot
            if(ot > 3 && list.get(1).getOt() < 3){
                if(ot > 3 && counter1 != 0 && iter.getLista()[1] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter() + 1], iter.getIter() + 1, counter1 * 15 + list.get(1).getAllpoint());}
                if(ot > 3  && counter1 != 0 && iter.getLista()[1] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter() + 1], iter.getIter() + 1, (ot - 3) * 15 + list.get(1).getAllpoint());
                    iter.setLista(1);
                }
            }
            if(ot > 3 && list.get(2).getOt() < 3){
                if(ot > 3  && counter1 != 0 && iter.getLista()[2] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,counter1*15+list.get(2).getAllpoint());
                }
                if(ot > 3  && counter1 != 0 && iter.getLista()[2] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,(ot-3)*15+list.get(2).getAllpoint());
                    iter.setLista(2);
                }
            }
            //hat
            int hat = res.getInt(4);
            if(hat > 3 && list.get(1).getHat() < 3){
                if(hat > 3 && counter2 != 0 && iter.getLista()[3] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter2*16+list.get(1).getAllpoint());
                }
                if(hat > 3 && counter2 != 0 && iter.getLista()[3] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(hat-3)*16+list.get(1).getAllpoint());
                    iter.setLista(3);
                }
            }

            if(hat > 3 && list.get(2).getHat() < 3){
                if(hat > 3 && counter2 != 0 && iter.getLista()[4] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,counter2*16+list.get(2).getAllpoint());
                }
                if(hat > 3 && counter2 != 0 && iter.getLista()[4] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,(hat-3)*16+list.get(2).getAllpoint());
                    iter.setLista(4);
                }
            }
            //het
            int het = res.getInt(5);
            if(het > 3 && list.get(1).getHet() < 3){
                if(het > 3 && counter3 != 0 && iter.getLista()[5] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter3*17+list.get(1).getAllpoint());
                }
                if(het > 3 && counter3 != 0 && iter.getLista()[5] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(het-3)*17+list.get(1).getAllpoint());
                    iter.setLista(5);
                }
            }
            if(het > 3 && list.get(2).getHet() < 3){
                if(het > 3 && counter3 != 0 && iter.getLista()[6] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,counter3*17+list.get(2).getAllpoint());
                }
                if(het > 3 && counter3 != 0 && iter.getLista()[6] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,(het-3)*17+list.get(2).getAllpoint());
                    iter.setLista(6);
                }
            }
            //nyolc
            int nyolc = res.getInt(6);
            if(nyolc > 3 && list.get(1).getNyolc() < 3){
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[7] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter4*18+list.get(1).getAllpoint());
                }
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[7] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(nyolc-3)*18+list.get(1).getAllpoint());
                    iter.setLista(7);
                }
            }
            if(nyolc > 3 && list.get(2).getNyolc() < 3){
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[8] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,counter4*18+list.get(2).getAllpoint());
                }
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[8] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,(nyolc-3)*18+list.get(2).getAllpoint());
                    iter.setLista(8);
                }
            }
            //kilenc
            int kilenc = res.getInt(7);
            if(kilenc > 3 && list.get(1).getKilenc() < 3){
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[9] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter5*19+list.get(1).getAllpoint());
                }
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[9] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(kilenc-3)*19+list.get(1).getAllpoint());
                    iter.setLista(9);
                }
            }
            if(kilenc > 3 && list.get(2).getKilenc() < 3){
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[10] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,counter5*19+list.get(2).getAllpoint());
                }
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[10] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,(kilenc-3)*19+list.get(2).getAllpoint());
                    iter.setLista(10);
                }
            }
            //husz
            int husz = res.getInt(8);
            if(husz > 3 && list.get(1).getHusz() < 3){
                if(husz > 3  && counter6 != 0 && iter.getLista()[11] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter6*20+list.get(1).getAllpoint());
                }
                if(husz > 3  && counter6 != 0 && iter.getLista()[11] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(husz-3)*20+list.get(1).getAllpoint());
                    iter.setLista(11);
                }
            }
            if(husz > 3 && list.get(2).getHusz() < 3){
                if(husz > 3  && counter6 != 0 && iter.getLista()[12] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,counter6*20+list.get(2).getAllpoint());
                }
                if(husz > 3  && counter6 != 0 && iter.getLista()[12] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,(husz-3)*20+list.get(2).getAllpoint());
                    iter.setLista(12);
                }
            }
            //huszonot
            int huszonot = res.getInt(9);
            if(huszonot > 3 && list.get(1).getHusz() < 3){
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[13] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter7*25+list.get(1).getAllpoint());
                }
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[13] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(huszonot-3)*25+list.get(1).getAllpoint());
                    iter.setLista(13);
                }
            }
            if(huszonot > 3 && list.get(2).getHusz() < 3){
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[14] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,counter7*25+list.get(2).getAllpoint());
                }
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[14] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+2],iter.getIter()+2,(huszonot-3)*25+list.get(2).getAllpoint());
                    iter.setLista(14);
                }
            }
        }
    }
    public void number11(){
        Cursor res = DB.getdata1();
        while(res.moveToNext()){
            int allpoint = res.getInt(2);
            numbers.setAllpoint(0+allpoint);
            int ot = res.getInt(3);
            //ot
            if(ot > 3 && list.get(0).getOt() < 3){
                if(ot > 3 && counter1 != 0 && iter.getLista()[15] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter() - 1], iter.getIter() - 1, counter1 * 15 + list.get(0).getAllpoint());}
                if(ot > 3  && counter1 != 0 && iter.getLista()[15] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter() - 1], iter.getIter() - 1, (ot - 3) * 15 + list.get(0).getAllpoint());
                    iter.setLista(15);
                }
            }
            if(ot > 3 && list.get(2).getOt() < 3){
                if(ot > 3  && counter1 != 0 && iter.getLista()[16] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter1*15+list.get(2).getAllpoint());
                }
                if(ot > 3  && counter1 != 0 && iter.getLista()[16] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(ot-3)*15+list.get(2).getAllpoint());
                    iter.setLista(16);
                }
            }
            //hat
            int hat = res.getInt(4);
            if(hat > 3 && list.get(0).getHat() < 3){
                if(hat > 3 && counter2 != 0 && iter.getLista()[17] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter2*16+list.get(0).getAllpoint());
                }
                if(hat > 3 && counter2 != 0 && iter.getLista()[17] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(hat-3)*16+list.get(0).getAllpoint());
                    iter.setLista(17);
                }
            }

            if(hat > 3 && list.get(2).getHat() < 3){
                if(hat > 3 && counter2 != 0 && iter.getLista()[18] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter2*16+list.get(2).getAllpoint());
                }
                if(hat > 3 && counter2 != 0 && iter.getLista()[18] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(hat-3)*16+list.get(2).getAllpoint());
                    iter.setLista(18);
                }
            }
            //het
            int het = res.getInt(5);
            if(het > 3 && list.get(0).getHet() < 3){
                if(het > 3 && counter3 != 0 && iter.getLista()[19] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter3*17+list.get(0).getAllpoint());
                }
                if(het > 3 && counter3 != 0 && iter.getLista()[19] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(het-3)*17+list.get(0).getAllpoint());
                    iter.setLista(19);
                }
            }
            if(het > 3 && list.get(2).getHet() < 3){
                if(het > 3 && counter3 != 0 && iter.getLista()[20] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter3*17+list.get(2).getAllpoint());
                }
                if(het > 3 && counter3 != 0 && iter.getLista()[20] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(het-3)*17+list.get(2).getAllpoint());
                    iter.setLista(20);
                }
            }
            //nyolc
            int nyolc = res.getInt(6);
            if(nyolc > 3 && list.get(0).getNyolc() < 3){
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[21] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter4*18+list.get(0).getAllpoint());
                }
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[21] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(nyolc-3)*18+list.get(0).getAllpoint());
                    iter.setLista(21);
                }
            }
            if(nyolc > 3 && list.get(2).getNyolc() < 3){
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[22] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter4*18+list.get(2).getAllpoint());
                }
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[22] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(nyolc-3)*18+list.get(2).getAllpoint());
                    iter.setLista(22);
                }
            }
            //kilenc
            int kilenc = res.getInt(7);
            if(kilenc > 3 && list.get(0).getKilenc() < 3){
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[23] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter5*19+list.get(0).getAllpoint());
                }
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[23] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(kilenc-3)*19+list.get(0).getAllpoint());
                    iter.setLista(23);
                }
            }
            if(kilenc > 3 && list.get(2).getKilenc() < 3){
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[24] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter5*19+list.get(2).getAllpoint());
                }
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[24] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(kilenc-3)*19+list.get(2).getAllpoint());
                    iter.setLista(24);
                }
            }
            //husz
            int husz = res.getInt(8);
            if(husz > 3 && list.get(0).getHusz() < 3){
                if(husz > 3  && counter6 != 0 && iter.getLista()[25] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter6*20+list.get(0).getAllpoint());
                }
                if(husz > 3  && counter6 != 0 && iter.getLista()[25] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(husz-3)*20+list.get(0).getAllpoint());
                    iter.setLista(25);
                }
            }
            if(husz > 3 && list.get(2).getHusz() < 3){
                if(husz > 3  && counter6 != 0 && iter.getLista()[26] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter6*20+list.get(2).getAllpoint());
                }
                if(husz > 3  && counter6 != 0 && iter.getLista()[26] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(husz-3)*20+list.get(2).getAllpoint());
                    iter.setLista(26);
                }
            }
            //huszonot
            int huszonot = res.getInt(9);
            if(huszonot > 3 && list.get(0).getHusz() < 3){
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[27] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter7*25+list.get(0).getAllpoint());
                }
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[27] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(huszonot-3)*25+list.get(0).getAllpoint());
                    iter.setLista(27);
                }
            }
            if(huszonot > 3 && list.get(2).getHusz() < 3){
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[28] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,counter7*25+list.get(2).getAllpoint());
                }
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[28] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()+1],iter.getIter()+1,(huszonot-3)*25+list.get(2).getAllpoint());
                    iter.setLista(28);
                }
            }
        }
    }
    public void number22(){
        Cursor res = DB.getdata2();
        while(res.moveToNext()){
            int allpoint = res.getInt(2);
            numbers.setAllpoint(0+allpoint);
            int ot = res.getInt(3);
            //ot
            if(ot > 3 && list.get(1).getOt() < 3){
                if(ot > 3 && counter1 != 0 && iter.getLista()[29] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter() - 1], iter.getIter() - 1, counter1 * 15 + list.get(1).getAllpoint());}
                if(ot > 3  && counter1 != 0 && iter.getLista()[29] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter() - 1], iter.getIter() - 1, (ot - 3) * 15 + list.get(1).getAllpoint());
                    iter.setLista(29);
                }
            }
            if(ot > 3 && list.get(0).getOt() < 3){
                if(ot > 3  && counter1 != 0 && iter.getLista()[30] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,counter1*15+list.get(0).getAllpoint());
                }
                if(ot > 3  && counter1 != 0 && iter.getLista()[30] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,(ot-3)*15+list.get(0).getAllpoint());
                    iter.setLista(30);
                }
            }
            //hat
            int hat = res.getInt(4);
            if(hat > 3 && list.get(1).getHat() < 3){
                if(hat > 3 && counter2 != 0 && iter.getLista()[31] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter2*16+list.get(1).getAllpoint());
                }
                if(hat > 3 && counter2 != 0 && iter.getLista()[31] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(hat-3)*16+list.get(1).getAllpoint());
                    iter.setLista(31);
                }
            }

            if(hat > 3 && list.get(0).getHat() < 3){
                if(hat > 3 && counter2 != 0 && iter.getLista()[32] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,counter2*16+list.get(0).getAllpoint());
                }
                if(hat > 3 && counter2 != 0 && iter.getLista()[32] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,(hat-3)*16+list.get(0).getAllpoint());
                    iter.setLista(32);
                }
            }
            //het
            int het = res.getInt(5);
            if(het > 3 && list.get(1).getHet() < 3){
                if(het > 3 && counter3 != 0 && iter.getLista()[33] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter3*17+list.get(1).getAllpoint());
                }
                if(het > 3 && counter3 != 0 && iter.getLista()[33] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(het-3)*17+list.get(1).getAllpoint());
                    iter.setLista(33);
                }
            }
            if(het > 3 && list.get(0).getHet() < 3){
                if(het > 3 && counter3 != 0 && iter.getLista()[34] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,counter3*17+list.get(0).getAllpoint());
                }
                if(het > 3 && counter3 != 0 && iter.getLista()[34] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,(het-3)*17+list.get(0).getAllpoint());
                    iter.setLista(34);
                }
            }
            //nyolc
            int nyolc = res.getInt(6);
            if(nyolc > 3 && list.get(1).getNyolc() < 3){
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[35] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter4*18+list.get(1).getAllpoint());
                }
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[35] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(nyolc-3)*18+list.get(1).getAllpoint());
                    iter.setLista(35);
                }
            }
            if(nyolc > 3 && list.get(0).getNyolc() < 3){
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[36] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,counter4*18+list.get(0).getAllpoint());
                }
                if(nyolc > 3  && counter4 != 0 && iter.getLista()[36] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,(nyolc-3)*18+list.get(0).getAllpoint());
                    iter.setLista(36);
                }
            }
            //kilenc
            int kilenc = res.getInt(7);
            if(kilenc > 3 && list.get(1).getKilenc() < 3){
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[37] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter5*19+list.get(1).getAllpoint());
                }
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[37] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(kilenc-3)*19+list.get(1).getAllpoint());
                    iter.setLista(37);
                }
            }
            if(kilenc > 3 && list.get(0).getKilenc() < 3){
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[38] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,counter5*19+list.get(0).getAllpoint());
                }
                if(kilenc > 3  && counter5 != 0 && iter.getLista()[38] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,(kilenc-3)*19+list.get(0).getAllpoint());
                    iter.setLista(38);
                }
            }
            //husz
            int husz = res.getInt(8);
            if(husz > 3 && list.get(1).getHusz() < 3){
                if(husz > 3  && counter6 != 0 && iter.getLista()[39] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter6*20+list.get(1).getAllpoint());
                }
                if(husz > 3  && counter6 != 0 && iter.getLista()[39] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(husz-3)*20+list.get(1).getAllpoint());
                    iter.setLista(39);
                }
            }
            if(husz > 3 && list.get(0).getHusz() < 3){
                if(husz > 3  && counter6 != 0 && iter.getLista()[40] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,counter6*20+list.get(0).getAllpoint());
                }
                if(husz > 3  && counter6 != 0 && iter.getLista()[40] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,(husz-3)*20+list.get(0).getAllpoint());
                    iter.setLista(40);
                }
            }
            //huszonot
            int huszonot = res.getInt(9);
            if(huszonot > 3 && list.get(1).getHusz() < 3){
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[41] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,counter7*25+list.get(1).getAllpoint());
                }
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[41] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-1],iter.getIter()-1,(huszonot-3)*25+list.get(1).getAllpoint());
                    iter.setLista(41);
                }
            }
            if(huszonot > 3 && list.get(0).getHusz() < 3){
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[42] == true) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,counter7*25+list.get(0).getAllpoint());
                }
                if(huszonot > 3  && counter7 != 0 && iter.getLista()[42] == false) {
                    DB.updateuserdataset(getallnames[iter.getIter()-2],iter.getIter()-2,(huszonot-3)*25+list.get(0).getAllpoint());
                    iter.setLista(42);
                }
            }
        }
    }
    public void Number0(){
        Cursor res = DB.getdata0();
            while(res.moveToNext()){
            int allpoint = res.getInt(2);
            numbers.setAllpoint(0+allpoint);
            int ot = res.getInt(3);
            //Log.d("ot",  Integer.toString(ot));
            //Log.d("ott",  Integer.toString(counter1));
            numbers.setOt(counter1+ot);
            int hat = res.getInt(4);
            numbers.setHat(counter2+hat);
            int het = res.getInt(5);
            numbers.setHet(counter3+het);
            int nyolc = res.getInt(6);
            numbers.setNyolc(counter4+nyolc);
            int kilenc = res.getInt(7);
            numbers.setKilenc(counter5+kilenc);
            int husz = res.getInt(8);
            numbers.setHusz(counter6+husz);
            int huszonot = res.getInt(9);
            numbers.setHuszonot(counter7+huszonot);
            }
    }
    public void Number1(){
        Cursor res = DB.getdata1();
        while(res.moveToNext()){
            int allpoint = res.getInt(2);
            numbers.setAllpoint(0+allpoint);
            int ot = res.getInt(3);
            //Log.d("ot",  Integer.toString(ot));
            //Log.d("ott",  Integer.toString(counter1));
            numbers.setOt(counter1+ot);
            int hat = res.getInt(4);
            numbers.setHat(counter2+hat);
            int het = res.getInt(5);
            numbers.setHet(counter3+het);
            int nyolc = res.getInt(6);
            numbers.setNyolc(counter4+nyolc);
            int kilenc = res.getInt(7);
            numbers.setKilenc(counter5+kilenc);
            int husz = res.getInt(8);
            numbers.setHusz(counter6+husz);
            int huszonot = res.getInt(9);
            numbers.setHuszonot(counter7+huszonot);
        }
    }
    public void Number2(){
        Cursor res = DB.getdata2();
        while(res.moveToNext()){
            int allpoint = res.getInt(2);
            numbers.setAllpoint(0+allpoint);
            int ot = res.getInt(3);
            //Log.d("ot",  Integer.toString(ot));
            //Log.d("ott",  Integer.toString(counter1));
            numbers.setOt(counter1+ot);
            int hat = res.getInt(4);
            numbers.setHat(counter2+hat);
            int het = res.getInt(5);
            numbers.setHet(counter3+het);
            int nyolc = res.getInt(6);
            numbers.setNyolc(counter4+nyolc);
            int kilenc = res.getInt(7);
            numbers.setKilenc(counter5+kilenc);
            int husz = res.getInt(8);
            numbers.setHusz(counter6+husz);
            int huszonot = res.getInt(9);
            numbers.setHuszonot(counter7+huszonot);
        }
    }
    public void CountClick1(View v){
        if (counter1 >= 8 && iter.getIgaze() <= 3){
            btn1.setEnabled(false);
        }
        if(counter1 == 0 ){iter.setNyil(iter.getNyil()-1);}
        if(counter1 == 3 ){iter.setNyil(iter.getNyil()-1);}
        if(counter1 == 6 ){iter.setNyil(iter.getNyil()-1);}
        counter1++;
        iter.setOsszertek(iter.getOsszertek()+1);
        btn1.setText("15-"+ String.valueOf(counter1));
        maxharomm();
    }
    public void CountClick2(View v){
        if (counter2 >= 8 && iter.getIgaze() <= 3){
            btn2.setEnabled(false);
        }
        if(counter2 == 0 ){iter.setNyil(iter.getNyil()-1);}
        if(counter2 == 3 ){iter.setNyil(iter.getNyil()-1);}
        if(counter2 == 6 ){iter.setNyil(iter.getNyil()-1);}
        counter2++;
        iter.setOsszertek(iter.getOsszertek()+1);
        btn2.setText("16-"+ String.valueOf(counter2));
        maxharomm();
    }
    public void CountClick3(View v){
        if (counter3 >= 8 && iter.getIgaze() <= 3){
            btn3.setEnabled(false);
        }
        if(counter3 == 0 ){iter.setNyil(iter.getNyil()-1);}
        if(counter3 == 3 ){iter.setNyil(iter.getNyil()-1);}
        if(counter3 == 6 ){iter.setNyil(iter.getNyil()-1);}
        counter3++;
        iter.setOsszertek(iter.getOsszertek()+1);
        btn3.setText("17-"+ String.valueOf(counter3));
        maxharomm();
    }
    public void CountClick4(View v){
        if (counter4 >= 8 && iter.getIgaze() <= 3){
            btn4.setEnabled(false);
        }
        if(counter4 == 0 ){iter.setNyil(iter.getNyil()-1);}
        if(counter4 == 3 ){iter.setNyil(iter.getNyil()-1);}
        if(counter4 == 6 ){iter.setNyil(iter.getNyil()-1);}
        counter4++;
        iter.setOsszertek(iter.getOsszertek()+1);
        btn4.setText("18-"+ String.valueOf(counter4));
        maxharomm();
    }
    public void CountClick5(View v){
        if (counter5 >= 8 && iter.getIgaze() <= 3){
            btn5.setEnabled(false);
        }
        if(counter5 == 0 ){iter.setNyil(iter.getNyil()-1);}
        if(counter5 == 3 ){iter.setNyil(iter.getNyil()-1);}
        if(counter5 == 6 ){iter.setNyil(iter.getNyil()-1);}
        counter5++;
        iter.setOsszertek(iter.getOsszertek()+1);
        btn5.setText("19-"+ String.valueOf(counter5));
        maxharomm();
    }
    public void CountClick6(View v){
        if (counter6 >= 8 && iter.getIgaze() <= 3){
            btn6.setEnabled(false);
        }
        if(counter6 == 0 ){iter.setNyil(iter.getNyil()-1);}
        if(counter6 == 3 ){iter.setNyil(iter.getNyil()-1);}
        if(counter6 == 6 ){iter.setNyil(iter.getNyil()-1);}
        counter6++;
        iter.setOsszertek(iter.getOsszertek()+1);
        btn6.setText("20-"+ String.valueOf(counter6));
        maxharomm();

    }
    public void CountClick7(View v){
        if (counter7 >= 8 && iter.getIgaze() <= 3){
            btn7.setEnabled(false);
        }
        if(counter7 == 0 ){iter.setNyil(iter.getNyil()-1);}
        if(counter7 == 3 ){iter.setNyil(iter.getNyil()-1);}
        if(counter7 == 6 ){iter.setNyil(iter.getNyil()-1);}
        counter7++;
        iter.setOsszertek(iter.getOsszertek()+1);
        btn7.setText("25-"+ String.valueOf(counter7));
        maxharomm();
    }
    public void onClick2(View arg0){
        nullaz();
        finish();
        startActivity(getIntent());
    }
    public void onClick3(View arg0){
        //Log.d("Undo",  list3.toString());
        Log.d("Undo",  players.getList().toString());
        boolean elso = false;
        boolean masodik = false;
        boolean harmadik = false;
        if(iter.getGombszamlalo() == 0) {
            if (iter.getIter() == 0 && masodik == false && harmadik == false) {
                DB.updateoldlist(getallnames[0], 0);
                DB.updateoldlist(getallnames[1], 1);
                DB.updateoldlist(getallnames[2], 2);
                iter.setIter(2);
                elso = true;

            }
            if (iter.getIter() == 1 && elso == false && harmadik == false) {
                DB.updateoldlist(getallnames[0], 0);
                DB.updateoldlist(getallnames[1], 1);
                DB.updateoldlist(getallnames[2], 2);
                iter.setIter(0);
                masodik = true;

            }
            if (iter.getIter() == 2 && masodik == false && elso == false) {
                DB.updateoldlist(getallnames[0], 0);
                DB.updateoldlist(getallnames[1], 1);
                DB.updateoldlist(getallnames[2], 2);
                iter.setIter(1);
                harmadik = true;
            }

        }

        nullaz();
        finish();
        startActivity(getIntent());
    }
    public void nullaz(){
        iter.setOsszertek(0);
        iter.setNyil(4);
        iter.setIgaze(0);
        maxharom = 0;
        counter1 = 0;
        btn1.setText("15");
        btn1.setEnabled(true);
        counter2 = 0;
        btn2.setText("16");
        btn2.setEnabled(true);
        counter3 = 0;
        btn3.setText("17");
        btn3.setEnabled(true);
        counter4 = 0;
        btn4.setText("18");
        btn4.setEnabled(true);
        counter5 = 0;
        btn5.setText("19");
        btn5.setEnabled(true);
        counter6 = 0;
        btn6.setText("20");
        btn6.setEnabled(true);
        counter7 = 0;
        btn7.setText("25");
        btn7.setEnabled(true);
        int ot=0,hat=0,het=0,nyolc=0,kilenc=0,husz=0,huszonot=0;
    }
    public void maxharomm(){
        gombnullazo();
        maxharom=0;
        if(counter1 != 0){
            ot = 1; }
        if(counter2 != 0){
            hat = 1; }
        if(counter3 != 0){
            het = 1; }
        if(counter4 != 0){
            nyolc = 1; }
        if(counter5 != 0){
            kilenc = 1; }
        if(counter6 != 0){
            husz = 1; }
        if(counter7 != 0){
            huszonot = 1; }
        maxharom = ot+hat+het+nyolc+kilenc+husz+huszonot;
        //Log.d("ALL",  Integer.toString(maxharom));
        iter.setIgaze(maxharom);
        //Log.d("Nyil",  Integer.toString(iter.getNyil()));

        if(maxharom > 2 || iter.getNyil() == 1){
            if (ot == 1){
                btn1.setEnabled(true);
                if (counter1 >= 3 && iter.getIgaze() <= 3){
                    btn1.setEnabled(false);
                }
                if(iter.getNyil() == 1 && counter1 <= 8){
                    btn1.setEnabled(true);
                    if(counter1 == 3 || counter1 == 6){btn1.setEnabled(false);}
                }
            }
            else{btn1.setEnabled(false);}
        if (hat == 1){
            btn2.setEnabled(true);
            if (counter2 >= 3 && iter.getIgaze() <= 3){
                btn2.setEnabled(false);
            }
            if(iter.getNyil() == 1 && counter2 <= 8){
                btn2.setEnabled(true);
                if(counter2 == 3 || counter2 == 6){btn2.setEnabled(false);}
            }
        }
        else{btn2.setEnabled(false);}
        if (het == 1){btn3.setEnabled(true);
        if (counter3 >= 3 && iter.getIgaze() <= 3){
            btn3.setEnabled(false);
        }if(iter.getNyil() == 1 && counter3 <= 8){
                btn3.setEnabled(true);
                if(counter3 == 3 || counter3 == 6){btn3.setEnabled(false);}
            }}
        else{btn3.setEnabled(false);}
        if (nyolc == 1){btn4.setEnabled(true);
        if (counter4 >= 3 && iter.getIgaze() <= 3){
            btn4.setEnabled(false);

        }if(iter.getNyil() == 1 && counter4 <= 8){
                btn4.setEnabled(true);
                if(counter4 == 3 || counter4 == 6){btn4.setEnabled(false);}
            }}
        else{btn4.setEnabled(false);}
        if (kilenc == 1){btn5.setEnabled(true);
        if (counter5 >= 3 && iter.getIgaze() <= 3){
            btn5.setEnabled(false);
        }if(iter.getNyil() == 1 && counter5 <= 8){
                btn5.setEnabled(true);
                if(counter5 == 3 || counter5 == 6){btn5.setEnabled(false);}
            }}
        else{btn5.setEnabled(false);}
        if (husz == 1){btn6.setEnabled(true);
        if (counter6 >= 3 && iter.getIgaze() <= 3){
            btn6.setEnabled(false);
        }if(iter.getNyil() == 1 && counter6 <= 8){
                btn6.setEnabled(true);
                if(counter6 == 3 || counter6 == 6){btn6.setEnabled(false);}
            }}
        else{btn6.setEnabled(false);}
        if (huszonot == 1){btn7.setEnabled(true);
        if (counter7 >= 3 && iter.getIgaze() <= 3){
            btn7.setEnabled(false);
        }if(iter.getNyil() == 1 && counter7 <= 8){
                btn7.setEnabled(true);
                if(counter7 == 3 || counter7 == 6){btn7.setEnabled(false);}
            }}
        else{btn7.setEnabled(false);}
        }

    }
    public void gombnullazo(){
        if(iter.getOsszertek() >= 9){
            Log.d("osszegertek9",  Integer.toString(iter.getOsszertek()));
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(false);
            btn5.setEnabled(false);
            btn6.setEnabled(false);
            btn7.setEnabled(false);
        }
    }
    public void jatekvege(){
        if(list.get(0).getOt()+counter1 >= 3){szam1++;}
        if(list.get(0).getHat()+counter2 >= 3){szam1++;}
        if(list.get(0).getHet()+counter3 >= 3){szam1++;}
        if(list.get(0).getNyolc()+counter4 >= 3){szam1++;}
        if(list.get(0).getKilenc()+counter5 >= 3){szam1++;}
        if(list.get(0).getHusz()+counter6 >= 3){szam1++;}
        if(list.get(0).getHuszonot()+counter7 >= 3){szam1++;}

        if(list.get(1).getOt()+counter1 >= 3){szam2++;}
        if(list.get(1).getHat()+counter2 >= 3){szam2++;}
        if(list.get(1).getHet()+counter3 >= 3){szam2++;}
        if(list.get(1).getNyolc()+counter4 >= 3){szam2++;}
        if(list.get(1).getKilenc()+counter5 >= 3){szam2++;}
        if(list.get(1).getHusz()+counter6 >= 3){szam2++;}
        if(list.get(1).getHuszonot()+counter7 >= 3){szam2++;}

        if(list.get(2).getOt()+counter1 >= 3){szam3++;}
        if(list.get(2).getHat()+counter2 >= 3){szam3++;}
        if(list.get(2).getHet()+counter3 >= 3){szam3++;}
        if(list.get(2).getNyolc()+counter4 >= 3){szam3++;}
        if(list.get(2).getKilenc()+counter5 >= 3){szam3++;}
        if(list.get(2).getHusz()+counter6 >= 3){szam3++;}
        if(list.get(2).getHuszonot()+counter7 >= 3){szam3++;}



    }
    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
    public void max(){
        max = Math.max(szam1, Math.max(szam2, szam3));
    }
    public void statisztika(){

        if(list2!=null){

            String[] array = new String[list2.size()];

            for(int i= 0;i < list2.size();i++) {
                array[i] = list2.get(i).getName();
            }
            //System.out.println(set);
            for(int i = 0; i < getallnames.length; i++) {
                if(!Arrays.asList(array).contains(getallnames[i]))
                    ar.add(getallnames[i]);
                if(Arrays.asList(array).contains(getallnames[i]))
                    ar2.add(getallnames[i]);
            }
            Log.d("array",  ar.get(0));
            Log.d("array",  Integer.toString(ar.size()));
            Log.d("array",  Integer.toString(list.size()));
            Log.d("array",  Names.getWinnername());

            System.out.println(ar);
            System.out.println(ar2);

            for(int j= 0;j < ar.size();j++) {
                for (int i = 0; i < list.size(); i++){
                    if (ar.get(j) == getallnames[i]){
                        if(ar.get(j).equals(Names.getWinnername())){
                            Log.d("UJNYERTES",  getallnames[i]);
                            DB2.insertstats(list.get(i).getName(), 1, list.get(i).getAllpoint(),1);
                        }else{
                            DB2.insertstats(list.get(i).getName(), 0, list.get(i).getAllpoint(),1);}
                    }
                }
            }

            for (int j = 0; j < list2.size(); j++) {
                    if (getallnames[0].equals(list2.get(j).getName())) {
                        if (maxname.equals(list2.get(j).getName())) {
                            if(iter.getKorszamlalo() <= list2.get(j).getKorszam()){DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem() + 1,iter.getKorszamlalo(),list2.get(j).getKisebbbuntetopont(), list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);}
                            else{DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem() + 1,list2.get(j).getKorszam(), list2.get(j).getKisebbbuntetopont(),list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);}
                            if(list.get(0).getAllpoint() <= list2.get(j).getKisebbbuntetopont()){DB2.updatekisebb(list2.get(j).getName(),list.get(0).getAllpoint());}
                            if(list.get(0).getAllpoint() >= list2.get(j).getNagyobbbuntetopont()){DB2.updatenagyobb(list2.get(j).getName(),list.get(0).getAllpoint());}
                        } else {
                            DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem(),iter.getKorszamlalo(), list2.get(j).getKisebbbuntetopont(),list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);
                            if(list.get(0).getAllpoint() <= list2.get(j).getKisebbbuntetopont()){DB2.updatekisebb(list2.get(j).getName(),list.get(0).getAllpoint());}
                            if(list.get(0).getAllpoint() >= list2.get(j).getNagyobbbuntetopont()){DB2.updatenagyobb(list2.get(j).getName(),list.get(0).getAllpoint());}
                        }
                    }
                    if (getallnames[1].equals(list2.get(j).getName())) {
                        if (maxname.equals(list2.get(j).getName())) {
                            if(iter.getKorszamlalo() <= list2.get(j).getKorszam()){DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem() + 1,iter.getKorszamlalo(),list2.get(j).getKisebbbuntetopont(), list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);}
                            else{DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem() + 1,list2.get(j).getKorszam(),list2.get(j).getKisebbbuntetopont(), list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);}
                            if(list.get(1).getAllpoint() <= list2.get(j).getKisebbbuntetopont()){DB2.updatekisebb(list2.get(j).getName(),list.get(0).getAllpoint());}
                            if(list.get(1).getAllpoint() >= list2.get(j).getNagyobbbuntetopont()){DB2.updatenagyobb(list2.get(j).getName(),list.get(0).getAllpoint());}
                        } else {
                            DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem(),iter.getKorszamlalo(), list2.get(j).getKisebbbuntetopont(),list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);
                            if(list.get(1).getAllpoint() <= list2.get(j).getKisebbbuntetopont()){DB2.updatekisebb(list2.get(j).getName(),list.get(0).getAllpoint());}
                            if(list.get(1).getAllpoint() >= list2.get(j).getNagyobbbuntetopont()){DB2.updatenagyobb(list2.get(j).getName(),list.get(0).getAllpoint());}
                        }
                    }
                    if (getallnames[2].equals(list2.get(j).getName())) {
                        if (maxname.equals(list2.get(j).getName())) {
                            if(iter.getKorszamlalo() <= list2.get(j).getKorszam()){DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem() + 1,iter.getKorszamlalo(),list2.get(j).getKisebbbuntetopont(), list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);}
                            else{DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem() + 1,list2.get(j).getKorszam(),list2.get(j).getKisebbbuntetopont(), list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);}
                            if(list.get(2).getAllpoint() <= list2.get(j).getKisebbbuntetopont()){DB2.updatekisebb(list2.get(j).getName(),list.get(0).getAllpoint());}
                            if(list.get(2).getAllpoint() >= list2.get(j).getNagyobbbuntetopont()){DB2.updatenagyobb(list2.get(j).getName(),list.get(0).getAllpoint());}
                        } else {
                            DB2.updateuserdata(list2.get(j).getName(), list2.get(j).getGyozelem(),iter.getKorszamlalo(), list2.get(j).getKisebbbuntetopont(),list2.get(j).getNagyobbbuntetopont(), list2.get(j).getJatekszam() + 1);
                            if(list.get(2).getAllpoint() <= list2.get(j).getKisebbbuntetopont()){DB2.updatekisebb(list2.get(j).getName(),list.get(0).getAllpoint());}
                            if(list.get(2).getAllpoint() >= list2.get(j).getNagyobbbuntetopont()){DB2.updatenagyobb(list2.get(j).getName(),list.get(0).getAllpoint());}
                        }
                    }

                }

        }
        if(list2==null){
            for(int i= 0;i < list.size();i++) {
                if(maxname == list.get(i).getName()){
                    DB2.insertstats(list.get(i).getName(),1,list.get(i).getAllpoint(),1);
                }
                else{DB2.insertstats(list.get(i).getName(),0,list.get(i).getAllpoint(),1);}
            }
        }
    }

}