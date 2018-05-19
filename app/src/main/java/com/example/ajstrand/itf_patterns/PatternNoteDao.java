package com.example.ajstrand.itf_patterns;

/**
 * Created by ajstrand on 3/15/2018.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface PatternNoteDao {

    @Query("SELECT * FROM patternnote")
    LiveData<List<PatternNote>> getAllNotes();

    @Query("SELECT * FROM patternnote WHERE id = :id")
    PatternNote getNote(int id);

    @Insert
    void insert(PatternNote notes);

    @Update
    void update(PatternNote... notes);

    @Delete
    void delete(PatternNote... notes);

    @Query("DELETE FROM patternnote")
    void deleteAll();
}
