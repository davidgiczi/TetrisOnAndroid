package com.david.giczi.tetris.app.db;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class GamerService {
    private static GamerDao gamerDao;
    public static List<Gamer> GAMERS;

    public GamerService(Context context) {
        GamerDatabase gamerDatabase = GamerDatabase.getInstance(context);
        GamerService.gamerDao = gamerDatabase.gamerDao();
    }

    public static void getAllGamers(){
        GAMERS = new ArrayList<>();
        GamerDatabase.databaseExecutor.execute(() -> GAMERS = gamerDao.getAllGamers());

    }

    public static void insertGamer(Gamer gamer){
        GamerDatabase.databaseExecutor.execute(() -> {
            gamerDao.insertGamer(gamer);
            GAMERS = gamerDao.getAllGamers();
        });
    }

    public static void deleteGamer(String gamerName){
        GamerDatabase.databaseExecutor.execute(() -> {
            Gamer gamer = gamerDao.getGamerByName(gamerName);
            gamerDao.deleteGamerById(gamer.getId());
            GAMERS = gamerDao.getAllGamers();
        });
    }

    public static Gamer getGamerByName(String name){
        Gamer gamer = null;
        gamer = GAMERS.stream().filter(g -> g.name.equals(name)).findFirst().orElse(null);
        return gamer;
    }
}

