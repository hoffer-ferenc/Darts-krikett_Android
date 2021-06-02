package com.example.darts;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, allpoints TEXT, ot TEXT, hat TEXT, het TEXT, nyolc TEXT, kilenc TEXT, husz TEXT, huszonot TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(int id, String name, int allpoints, int ot, int hat, int het, int nyolc, int kilenc, int husz, int huszonot)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("allpoints", allpoints);
        contentValues.put("ot", ot);
        contentValues.put("hat", hat);
        contentValues.put("het", het);
        contentValues.put("nyolc", nyolc);
        contentValues.put("kilenc", kilenc);
        contentValues.put("husz", husz);
        contentValues.put("huszonot", huszonot);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdataujj(String name,int id) {
        Numbers numbers = new Numbers();
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("allpoints", numbers.getAllpoint());
        contentValues.put("ot", numbers.getOt());
        contentValues.put("hat", numbers.getHat());
        contentValues.put("het", numbers.getHet());
        contentValues.put("nyolc", numbers.getNyolc());
        contentValues.put("kilenc", numbers.getKilenc());
        contentValues.put("husz", numbers.getHusz());
        contentValues.put("huszonot", numbers.getHuszonot());
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}
    public Boolean updateoldlist(String name,int id) {
        Players players = new Players();
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("allpoints", players.getList().get(id).getAllpoint());
        contentValues.put("ot", players.getList().get(id).getOt());
        contentValues.put("hat", players.getList().get(id).getHat());
        contentValues.put("het", players.getList().get(id).getHet());
        contentValues.put("nyolc", players.getList().get(id).getNyolc());
        contentValues.put("kilenc", players.getList().get(id).getKilenc());
        contentValues.put("husz", players.getList().get(id).getHusz());
        contentValues.put("huszonot", players.getList().get(id).getHuszonot());
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

    public Boolean updateuserdataset(String name,int id, int buntetopont) {
        Numbers numbers = new Numbers();
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("allpoints", buntetopont);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails", null);
        return cursor;
    }
    public Cursor getdata0()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE id=0", null);
        return cursor;
    }
    public Cursor getdata1()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE id=1", null);
        return cursor;
    }
    public Cursor getdata2()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE id=2", null);
        return cursor;
    }

    public ArrayList<Players> getAllData(){
        ArrayList<Players> arrayList = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int allpoint = cursor.getInt(2);
            int ot = cursor.getInt(3);
            int hat = cursor.getInt(4);
            int het = cursor.getInt(5);
            int nyolc = cursor.getInt(6);
            int kilenc = cursor.getInt(7);
            int husz = cursor.getInt(8);
            int huszonot = cursor.getInt(9);
            Players targy = new Players(id,name,allpoint,ot,hat,het,nyolc,kilenc,husz,huszonot);
            arrayList.add(targy);
        }
        return arrayList;
    }
}