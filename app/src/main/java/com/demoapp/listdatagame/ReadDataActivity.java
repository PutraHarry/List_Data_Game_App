package com.demoapp.listdatagame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadDataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Game> daftarGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data2);
        getSupportActionBar().setTitle("Daftar Game");

        recyclerView = (RecyclerView) findViewById(R.id.dataItem);


        //inisialisasi arraylist
        daftarGame = new ArrayList<>();

        //inisialisasi RoomDatabase
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "db_Game").allowMainThreadQueries().build();

        /*
         *mengambil data game dari database
         * lalu memasukkannya ke dalam arraylist (daftarGame)
         */
        daftarGame.addAll(Arrays.asList(database.gameDAO().readDataGame()));

        //menentukann bagaimana item pada recyclerview akan tampil
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Agar ukuran RecyclerView tidak berubah
        recyclerView.setHasFixedSize(true);

        //memasang adapter pada recyclerview
        adapter = new RecyclerGameAdapter(daftarGame, ReadDataActivity.this);
        recyclerView.setAdapter(adapter);

    }
}