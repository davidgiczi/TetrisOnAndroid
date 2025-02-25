package com.david.giczi.tetris.app.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GamerDao {

    @Insert
    void insertGamer(Gamer gamer);
    @Update
    void updateGamer(Gamer gamer);
    @Query("SELECT * FROM gamers")
    List<Gamer> getAllGamers();
    @Query("DELETE FROM gamers WHERE id = :gamerId")
    void deleteGamerById(int gamerId);
    @Query("SELECT * FROM gamers WHERE name = :gamerName")
    Gamer getGamerByName(String gamerName);
    @Query("DELETE FROM gamers")
     void deleteAllGamers();

}
