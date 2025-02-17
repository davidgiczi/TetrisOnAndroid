package com.david.giczi.tetris.app.db;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class GamerService {

    private static GamerDao gamerDao;
    public static List<Gamer> GAMERS;

    public static void getAllGamers(Context context){
        GAMERS = new ArrayList<>();
        GamerDatabase gamerDatabase = GamerDatabase.getInstance(context);
        gamerDao = gamerDatabase.gamerDao();
        GamerDatabase.databaseExecutor.execute(() ->{
            GAMERS = gamerDao.getAllGamers();
        });
    }

    public static void insertGamer(Gamer gamer){
        GamerDatabase.databaseExecutor.execute(() ->{
           gamerDao.insert(gamer);
           GAMERS = gamerDao.getAllGamers();
        });
    }

    public static void deleteGamer(String gamerName){
        GamerDatabase.databaseExecutor.execute(() ->{
            Gamer gamer = gamerDao.getGamerByName(gamerName);
            gamerDao.deleteGamerById(gamer.getId());
            GAMERS = gamerDao.getAllGamers();
        });
    }
}
