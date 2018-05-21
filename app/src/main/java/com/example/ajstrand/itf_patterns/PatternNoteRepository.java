package com.example.ajstrand.itf_patterns;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by ajstrand on 3/15/2018.
 */

public class PatternNoteRepository {
    private PatternNoteDao daoInstance;
    private LiveData<List<PatternNote>> patternNotes;

   PatternNoteRepository(Application application){
       PatternNoteDatabase db = PatternNoteDatabase.getInstance(application);
       daoInstance = db.getPatternNoteDao();
       patternNotes = daoInstance.getAllNotes();
   }

   LiveData<List<PatternNote>> getPatterNotes(){
       return patternNotes;
   }

    PatternNote getPatterNote(int id){
        PatternNote note = daoInstance.getNote(id);
        return note;
    }

   public void insert(PatternNote note){
       new insertAsyncTask(daoInstance).execute(note);
   }

    public void update(PatternNote note) {
        new updateAsyncTask(daoInstance).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<PatternNote, Void, Void> {

        private PatternNoteDao mAsyncTaskDao;

        insertAsyncTask(PatternNoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PatternNote... patternNote) {
            mAsyncTaskDao.insert(patternNote[0]);
            return null;
        }
    }

    private class updateAsyncTask extends AsyncTask<PatternNote, Void, Void>  {
        private PatternNoteDao mAsyncTaskDao;

        public updateAsyncTask(PatternNoteDao daoInstance) {
            mAsyncTaskDao = daoInstance;
        }

        @Override
        protected Void doInBackground(final PatternNote... patternNote) {
            mAsyncTaskDao.update(patternNote[0]);
            return null;
        }
    }
}
