package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_isim, et_soyisim, et_adres;
    Button btn_kaydet, btn_guncelle, btn_sil;
    ListView lst_liste;
    int id;


    public void listele()
    {
        Veritabani vt=new Veritabani(MainActivity.this);
        List<String> liste=vt.Listele();
        ArrayAdapter<String> adapter= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,liste);
        lst_liste.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        et_isim=(EditText) findViewById(R.id.et_isim);
        et_soyisim = (EditText) findViewById(R.id.et_Soyisim);
        et_adres=(EditText) findViewById(R.id.et_Adres);

        btn_kaydet=(Button) findViewById(R.id.btn_Kaydet);
        btn_guncelle=(Button) findViewById(R.id.btn_Guncelle);
        btn_sil= (Button) findViewById(R.id.btn_Sil);

        lst_liste=(ListView) findViewById(R.id.lst_liste);

        listele();

        lst_liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { // int i değeri tıklanan satırın index numarasınını saklar

                String tiklananveri= lst_liste.getItemAtPosition(i).toString();

                String[] veriler= tiklananveri.split("-");

                id= Integer.parseInt(veriler[0].toString());

                et_isim.setText(veriler[1].toString());
                et_soyisim.setText(veriler[2].toString());
                et_adres.setText(veriler[3].toString());






            }
        });

        btn_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Veritabani vt=new Veritabani(MainActivity.this);
                String isim= et_isim.getText().toString();
                String soyisim=et_soyisim.getText().toString();
                String adres=et_adres.getText().toString();

                vt.Guncelle(id,isim,soyisim,adres);

                et_isim.setText("");
                et_adres.setText("");
                et_soyisim.setText("");

                listele();

                //Toast eklenebilir
            }
        });

        btn_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Veritabani vt=new Veritabani(MainActivity.this);
                vt.Sil(id);

                et_isim.setText("");
                et_adres.setText("");
                et_soyisim.setText("");

                listele();

                //Toast eklenebilir

            }
        });

        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Veritabani vt=new Veritabani(MainActivity.this);
                vt.Kaydet(et_isim.getText().toString(),et_soyisim.getText().toString(),et_adres.getText().toString());

                et_isim.setText("");
                et_soyisim.setText("");
                et_adres.setText("");

               listele();

                Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Eklendi", Toast.LENGTH_LONG).show(); }
        });
    }
}