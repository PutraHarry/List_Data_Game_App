package com.demoapp.listdatagame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import androidx.room.Room;
import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.demoapp.listdatagame.R;
import com.demoapp.listdatagame.Game;
import com.demoapp.listdatagame.AppDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText ID, Nama, Publisher, Kategori;
    private AppDatabase database;
    private Button bSimpan, bLihatData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ID = findViewById(R.id.id);
        Nama = findViewById(R.id.nama);
        Publisher = findViewById(R.id.publisher);
        Kategori = findViewById(R.id.kategori);
        bSimpan = findViewById(R.id.save);
        bSimpan.setOnClickListener(this);
        bLihatData = findViewById(R.id.show);
        bLihatData.setOnClickListener(this);

        //inisialisasi dan memanggil Room Database
        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "db_Game") //nama database yang akan dibuat
                .build();
    }

    //Menjalankann methid Insert Data
    @SuppressLint("StaticFieldLeak")
    private void insertData (final Game game) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground (Void... voids) {
                //Menjalankan proses insert data
                return database.gameDAO().insertGame(game);
            }

            @Override
            protected void onPostExecute (Long status) {
                //menandakan data berhasil disimpan
                Toast.makeText(MainActivity.this, "Status Row"+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.save:

                //Mengecek Data ID dan Nama
                if(ID.getText().toString().isEmpty() || Nama.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "ID atau Nama Game tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Membuat Instance/Objek dari class Entity Game
                    Game data = new Game();

                    //Memasukkan data yang diinputkan user pada database
                    data.setId(ID.getText().toString());
                    data.setNama(Nama.getText().toString());
                    data.setPublisher(Publisher.getText().toString());
                    data.setKategori(Kategori.getText().toString());
                    insertData(data);

                    //Mengembalikan EditText menjadi seperti semula
                    ID.setText("");
                    Nama.setText("");
                    Publisher.setText("");
                    Kategori.setText("");
                }
                break;

            case R.id.show:
                startActivity(new Intent(MainActivity.this, ReadDataActivity.class));
                break;
        }

    }
}