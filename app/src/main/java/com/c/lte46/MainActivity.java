package com.c.lte46;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText stb,nama, angk;
    private Button simpan, tampil;
    private DBHelper dbHelper;
    private Mahasiswa mhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DBHelper(this);

        stb=findViewById(R.id.txt_Stb);
        nama=findViewById(R.id.txt_nama);
        angk=findViewById(R.id.txt_angkatan);

        simpan=findViewById(R.id.btnSimpan);
        tampil=findViewById(R.id.btnTampil);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smpData();
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmp=new Intent(MainActivity.this, tampildata.class );
                startActivity(tmp);
                dbHelper.close();
            }
        });
    }

    private void smpData(){
        mhs=new Mahasiswa(
                stb.getText().toString(),
                nama.getText().toString(),
                angk.getText().toString()
        );

        dbHelper.insertData(dbHelper.getWritableDatabase(), mhs);

        Toast.makeText(this, "Data berhasil disimpan !", Toast.LENGTH_SHORT).show();
        bersih();
    }

    private void bersih(){
        stb.setText("");
        nama.setText("");
        angk.setText("");
        stb.requestFocus();
    }
}
