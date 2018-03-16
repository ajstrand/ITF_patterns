package com.example.ajstrand.itf_patterns;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by ajstrand on 3/15/2018.
 */

public class PatternNoteViewModel extends AndroidViewModel {
    private PatternNoteRepository mRep;
    private LiveData<List<PatternNote>> mPatternNotes;
    public PatternNoteViewModel(@NonNull Application application) {
        super(application);
        mRep = new PatternNoteRepository(application);
        mPatternNotes = mRep.getPatterNotes();
    }

    LiveData<List<PatternNote>> getPatternNotes(){
        return mPatternNotes;
    }

    public void insert(PatternNote word) { mRep.insert(word); }
}
