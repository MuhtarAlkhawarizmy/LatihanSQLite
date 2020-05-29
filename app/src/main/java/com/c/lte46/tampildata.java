package com.c.lte46;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class tampildata extends AppCompatActivity {
    Button ttp, edt, hps;
    private DBHelper dbHelper;
    private TableLayout tbMhs;
    private TableRow tr;
    private TextView col1,col2, col3;
    private ArrayList<Mahasiswa> arlMhs=new ArrayList<>();
    private String stb, nama, angkatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampildata);

        dbHelper=new DBHelper(this);
        ttp=findViewById(R.id.btn_tutup);
        edt=findViewById(R.id.btn_edit);
        hps=findViewById(R.id.btn_hapus);
        tbMhs=findViewById(R.id.tb_mhs);

        tampilTblMhs();

        hps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.hapusData(dbHelper.getWritableDatabase(), stb);
                tampilTblMhs();
            }
        });

        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("stb", stb);
                intent.putExtra("nama", nama);
                intent.putExtra("angkatan", angkatan);
                dbHelper.close();
                setResult(1, intent);
                finish();
            }
        });

        ttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void tampilTblMhs(){
        tbMhs.removeAllViews();
        arlMhs=dbHelper.getMhsArrayList(dbHelper.getWritableDatabase());
        tr=new TableRow(this);
        col1=new TextView(this);
        col2=new TextView(this);
        col3=new TextView(this);

        col1.setText("Stambuk");
        col2.setText("Nama Mahasiswa");
        col3.setText("Angkatan");

        col1.setWidth(300);
        col2.setWidth(400);
        col3.setWidth(250);

        tr.addView(col1);
        tr.addView(col2);
        tr.addView(col3);

        tbMhs.addView(tr);

        for (final Mahasiswa mhs: arlMhs){
            tr=new TableRow(this);
            col1=new TextView(this);
            col2=new TextView(this);
            col3=new TextView(this);

            col1.setText(mhs.getStb());
            col2.setText(mhs.getNama());
            col3.setText(mhs.getAngkatan());

            col1.setWidth(300);
            col2.setWidth(400);
            col3.setWidth(250);

            tr.addView(col1);
            tr.addView(col2);
            tr.addView(col3);

            tbMhs.addView(tr);

            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0; i<tbMhs.getChildCount(); i++){
                        stb=mhs.getStb();
                        nama=mhs.getNama();
                        angkatan=mhs.getAngkatan();
                        if(tbMhs.getChildAt(i)==v)
                            tbMhs.getChildAt(i).setBackgroundColor(Color.LTGRAY);
                        else
                            tbMhs.getChildAt(i).setBackgroundColor(Color.WHITE);
                    }
                }
            });
        }
    }
}
