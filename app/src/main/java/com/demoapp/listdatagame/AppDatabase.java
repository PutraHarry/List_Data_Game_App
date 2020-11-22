package com.demoapp.listdatagame;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.demoapp.listdatagame.GameDAO;
import com.demoapp.listdatagame.Game;

/*
 * Membuat class database
 * entitas yang dipakai adalah Game.class
 * version - 1
 */

@Database(entities = {Game.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   //untuk mengakses database menggunakan methid abstract
   public abstract GameDAO gameDAO();
}
