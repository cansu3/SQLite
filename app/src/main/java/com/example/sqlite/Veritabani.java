package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {

    private static final String VeritabaniAdi="Deneme";
    private static final int Versiyon=1;

    public Veritabani(Context c)
    {
       super(c,VeritabaniAdi,null,Versiyon);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create Table deneme" +
                "(ID integer Primary Key autoincrement," +
                "isim text," +
                "soyisim text," +
                "adres text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("Drop table if exists deneme");
    }

    public void Guncelle(int p_id, String p_isim, String p_soyisim, String p_adres)
    {
        SQLiteDatabase veri= this.getWritableDatabase();
        ContentValues sepet= new ContentValues();
        String sorgu="ID=" + p_id; //Hangi kaydın güncelleneceği
        sepet.put("isim",p_isim);
        sepet.put("soyisim",p_soyisim);
        sepet.put("adres",p_adres);
        veri.update("deneme",sepet,sorgu,null);

    }

    public void Sil(int p_id)
    {
        SQLiteDatabase veri= this.getWritableDatabase();
        String sorgu="ID=" + p_id;
        veri.delete("deneme",sorgu,null);

        veri.close();

    }

    public void Kaydet(String p_isim, String p_soyisim, String p_adres)
    {
        SQLiteDatabase vt= this.getWritableDatabase();
        ContentValues sepet= new ContentValues();
        sepet.put("isim",p_isim);
        sepet.put("soyisim",p_soyisim);
        sepet.put("adres",p_adres);
        vt.insert("deneme",null,sepet);

    }

    public List<String> Listele()
    {
        List<String> liste=new ArrayList<>();

        SQLiteDatabase vt= this.getReadableDatabase();

        String[] sutunlar={"ID","isim","soyisim", "adres" };

        Cursor cursor= vt.query("deneme", sutunlar,null,null,null, null,null);

        while(cursor.moveToNext())
        {
            liste.add((cursor.getString(0) + "-" + cursor.getString(1) + "-" +
                    cursor.getString(2) + "-" + cursor.getString(3)));

        }

        return liste;
    }
}
