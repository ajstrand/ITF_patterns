package com.example.ajstrand.itf_patterns;

/**
 * Created by ajstrand on 3/15/2018.
 */

    import android.arch.persistence.db.SupportSQLiteDatabase;
    import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
    import android.os.AsyncTask;
    import android.support.annotation.NonNull;
    @Database(entities = { PatternNote.class }, version = 1, exportSchema = false)
    public abstract class PatternNoteDatabase extends RoomDatabase {

        private static final String DB_NAME = "patternNoteDatabase.db";
        private static volatile PatternNoteDatabase instance;

        static synchronized PatternNoteDatabase getInstance(Context context) {
            if (instance == null) {
                instance = create(context);
            }
            return instance;
        }

        private static PatternNoteDatabase create(final Context context) {
            return Room.databaseBuilder(
                    context,
                    PatternNoteDatabase.class,
                    DB_NAME)
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }

        public abstract PatternNoteDao getPatternNoteDao();

        private static RoomDatabase.Callback sRoomDatabaseCallback =
                new RoomDatabase.Callback(){

                    @Override
                    public void onOpen (@NonNull SupportSQLiteDatabase db){
                        super.onOpen(db);
                        new PopulateDbAsync(instance).execute();
                    }
                };

        private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

            private final PatternNoteDao mDao;

            PopulateDbAsync(PatternNoteDatabase db) {
                mDao = db.getPatternNoteDao();
            }

            @Override
            protected Void doInBackground(final Void... params) {
                mDao.deleteAll();
                PatternNote note = new PatternNote("test", "this is a note");
                PatternNote second = new PatternNote("foo", "bar");
                mDao.insert(note);
                mDao.insert(second);
                return null;
            }
        }
}
