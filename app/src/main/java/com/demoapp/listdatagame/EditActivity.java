package com.demoapp.listdatagame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EditActivity extends AppCompatActivity {

    private TextInputEditText Nama, Publisher, Kategori;
    private AppDatabase database;
    private Button bSimpan;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Nama = findViewById(R.id.nama);
        Publisher = findViewById(R.id.publisher);
        Kategori = findViewById(R.id.kategori);
        bSimpan = findViewById(R.id.save);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "db_Game").build();

        //menampilkan data game yng akan diedit
        getDataGame();

        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.setNama(Nama.getText().toString());
                game.setPublisher(Publisher.getText().toString());
                game.setKategori(Kategori.getText().toString());
                updateData(game);
            }
        });
    }


    //method untuk menampilkan data game yang akan diedit
    private void getDataGame() {
        //mendapatkan data dari intent
        game = (Game)getIntent().getSerializableExtra("data");

        Nama.setText(game.getNama());
        Publisher.setText(game.getPublisher());
        Kategori.setText(game.getKategori());
    }

    //menjalankan method update data
    @SuppressLint("StaticFieldLeak")
    private void updateData(final Game game) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.gameDAO().updateGame(game);
            }

            @Override
            protected void onPostExecute (Integer status) {
                //Menandakann bahwa data berhasil disimpan
                Toast.makeText(EditActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this, ReadDataActivity.class));
                finish();
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditActivity.this, ReadDataActivity.class));
        finish();
    }


}