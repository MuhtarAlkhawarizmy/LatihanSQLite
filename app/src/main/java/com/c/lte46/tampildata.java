package com.c.lte46;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class tampildata extends AppCompatActivity {
    Button ttp, edt, hps;
    private DBHelper dbHelper;
    TableLayout tbMhs;
    TableRow tr;
    TextView col1,col2, col3;
    private ArrayList<Mahasiswa> arlMhs=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampildata);

        dbHelper=new DBHelper(this);
        ttp=findViewById(R.id.btn_tutup);
        edt=findViewById(R.id.btn_edit);
        hps=findViewById(R.id.btn_hapus);
        tbMhs=findViewById(R.id.tb_mhs);

        arlMhs=dbHelper.getMhsArrayList(dbHelper.getWritableDatabase());
        tr=new TableRow(this);
        col1=new TextView(this);
        col2=new TextView(this);
        col3=new TextView(this);

        col1.setText("Stambuk");
        col2.setText("Nama Mahasiswa");
        col3.setText("Angkatan");

        tr.addView(col1);
        tr.addView(col2);
        tr.addView(col3);

        tbMhs.addView(tr);

        for (Mahasiswa mhs: arlMhs){
            tr=new TableRow(this);
            col1=new TextView(this);
            col2=new TextView(this);
            col3=new TextView(this);

            col1.setText(mhs.getStb());
            col2.setText(mhs.getNama());
            col3.setText(mhs.getAngkatan());

            tr.addView(col1);
            tr.addView(col2);
            tr.addView(col3);

            tbMhs.addView(tr);
        }
    }
}
