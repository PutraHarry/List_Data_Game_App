package com.demoapp.listdatagame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class DetailDataActivity extends AppCompatActivity {

    private EditText getID, getName, getPublisher, getKategori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        getID = findViewById(R.id.gameID);
        getName = findViewById(R.id.gameName);
        getPublisher = findViewById(R.id.gamePublisher);
        getKategori = findViewById(R.id.gamekategori);

        getDetailData();
    }

    //Mendapatkan data yang akan ditampilkan secara detail
    private void getDetailData() {
        Game game = (Game)getIntent().getSerializableExtra("detail");

        //menampilkan data game pada activity
        if(game != null) {
            getID.setText(game.getId());
            getName.setText(game.getNama());
            getPublisher.setText(game.getPublisher());
            getKategori.setText(game.getKategori());
        }
    }
}