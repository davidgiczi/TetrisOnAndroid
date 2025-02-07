package com.david.giczi.tetris.app.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Gamer.class}, version = 1)
public abstract class GamerDatabase extends RoomDatabase {

    private static volatile GamerDatabase INSTANCE;
    public abstract GamerDao gamerDao();

    public static GamerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (GamerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GamerDatabase.class, "gamer_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
