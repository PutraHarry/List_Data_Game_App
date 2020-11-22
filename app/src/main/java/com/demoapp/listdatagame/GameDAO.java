package com.demoapp.listdatagame;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.demoapp.listdatagame.Game;

@Dao
public interface GameDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    long insertGame (Game game);

    @Query("SELECT * FROM tb_Game")
    Game[] readDataGame();

    @Update
    int updateGame (Game game);

    @Delete
    void deleteGame (Game game);

    @Query("SELECT * FROM tb_Game WHERE id_game = :id LIMIT 1")
    Game selectDetailGame (String id);
}
