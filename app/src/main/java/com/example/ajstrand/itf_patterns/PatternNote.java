package com.example.ajstrand.itf_patterns;

/**
 * Created by ajstrand on 3/15/2018.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class PatternNote {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String text;

    PatternNote(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public int getID(){return this.id;}

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
