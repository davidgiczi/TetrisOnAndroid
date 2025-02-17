package com.david.giczi.tetris.app.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

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
        if( date == 0 ){
            return "-";
        }
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
    }

    public int getCredit(){
        if( duration ==  0 ){
            return 0;
        }
        return score / duration;
    }

    public int getDuration() {
        return duration;
    }

    public int getScore() {
        return score;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return Objects.equals(name, gamer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @NonNull
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
        if( this.getDuration() == 0 || o.getDuration() == 0 ){
            return 0;
        }
        return Integer.compare(o.getScore() / o.getDuration(), this.getScore() / this.getDuration());
    }
}
