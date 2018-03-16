package com.example.ajstrand.itf_patterns;

/**
 * Created by ajstrand on 3/15/2018.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class PatternNote {
    @PrimaryKey
    public final int id;
    public final String name;
    public final String text;

    public PatternNote(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }
}
