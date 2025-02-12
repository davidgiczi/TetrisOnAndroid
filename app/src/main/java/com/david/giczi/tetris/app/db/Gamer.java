package com.david.giczi.tetris.app.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Entity(tableName = "gamers")
public class Gamer implements Comparable<Gamer>{

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "date")
    public long date;
    @ColumnInfo(name = "duration")
    public int duration;
    @ColumnInfo(name = "score")
    public int score;

    public Gamer(String name, int score, int duration, long date) {
        this.name = name;
        this.score = score;
        this.duration = duration;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
    }

    public int getCredit(){
        return score / duration;
    }

    public int getDuration() {
        return duration;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Gamer o) {
        return Integer.compare(this.score / this.duration, o.score / o.duration);
    }
}
