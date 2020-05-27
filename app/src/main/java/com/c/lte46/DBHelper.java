package com.c.lte46;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private ArrayList<Mahasiswa> mhsArrayList = new ArrayList<>();
    public DBHelper(Context ctx){
        super(ctx, "db mhs", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table tb_mhs(stb text(11) primary key, nama text(50), angkatan text(4))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertData(SQLiteDatabase db, Mahasiswa mhs){
        ContentValues cv=new ContentValues();
        cv.put("stb", mhs.getStb());
        cv.put("nama", mhs.getNama());
        cv.put("angkatan", mhs.getAngkatan());
        db.insert("tb_mhs", null, cv);
    }

    public ArrayList<Mahasiswa> getMhsArrayList(SQLiteDatabase db){
        mhsArrayList.clear();
        Cursor csr= db.rawQuery("select * from tb_mhs", null);
        if(csr.getCount()>0){
            csr.moveToFirst();
            do {
                mhsArrayList.add(new Mahasiswa(csr.getString(0), csr.getString(1), csr.getString(3)));
            }while (csr.moveToNext());
        }
        return mhsArrayList;
    }
}
