package com.demoapp.listdatagame;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//membuat tabel game
@Entity(tableName = "tb_Game")
public class Game implements Serializable {
    //membuat kolom Id game sebagai primary key
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id_game")
    private
    String id;

    @ColumnInfo(name = "nama_game")
    private
    String nama;

    @ColumnInfo(name = "publisher")
    private
    String publisher;

    @ColumnInfo(name = "kategori")
    private
    String kategori;

    //method untuk mengambil data id
    @NonNull
    public String getId() {
        return id;
    }

    //method untuk memasukan data id
    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

}
