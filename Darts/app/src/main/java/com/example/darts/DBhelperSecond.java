package com.example.darts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBhelperSecond extends SQLiteOpenHelper {

    public DBhelperSecond(Context context) {
        super(context, "Stats.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Stats(name TEXT PRIMARY KEY,korszam TEXT,gyozelem TEXT,kisebbbuntetopont TEXT,nagyobbbuntetopont TEXT,jatekszam TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Stats");
    }
    //nev,korszam,gyozelemszam
    public Boolean insertstats(String name,int gyozelem,int buntetopont,int jatekszam)
    {   SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("korszam", iteratior.getKorszamlalo()+1);
        contentValues.put("gyozelem", gyozelem);
        contentValues.put("kisebbbuntetopont", buntetopont);
        contentValues.put("nagyobbbuntetopont", buntetopont);
        contentValues.put("jatekszam", jatekszam);
        long result=DB.insert("Stats", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public ArrayList<Statistics> getAllData(){
        ArrayList<Statistics> arrayList = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Stats", null);

        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            int korszam = cursor.getInt(1);
            int gyozelem = cursor.getInt(2);
            int kisebbbuntetopont = cursor.getInt(3);
            int nagyobbbuntetopont = cursor.getInt(4);
            int jatekszam = cursor.getInt(5);
            Statistics stat = new Statistics(name,korszam,gyozelem,kisebbbuntetopont,nagyobbbuntetopont,jatekszam);
            arrayList.add(stat);
        }
        //DB.close();
        return arrayList;

    }

    public Boolean updateuserdata(String name,int gyozelem,int korszam,int buntetopont1,int buntetopont2,int jatekszam) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("gyozelem", gyozelem);
        contentValues.put("korszam", korszam);
        contentValues.put("kisebbbuntetopont", buntetopont1);
        contentValues.put("nagyobbbuntetopont", buntetopont2);
        contentValues.put("jatekszam", jatekszam);
        Cursor cursor = DB.rawQuery("Select * from Stats where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Stats", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}
    public Boolean updatekisebb(String name,int buntetopont1) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("kisebbbuntetopont", buntetopont1);
        Cursor cursor = DB.rawQuery("Select * from Stats where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Stats", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}
    public Boolean updatenagyobb(String name,int buntetopont2) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("nagyobbbuntetopont", buntetopont2);
        Cursor cursor = DB.rawQuery("Select * from Stats where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Stats", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Stats", null);
        return cursor;
    }

}
