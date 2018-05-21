package com.example.ajstrand.itf_patterns;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShowNote <T extends ViewModel> extends AppCompatActivity implements LifecycleOwner {

    boolean editing = false;

    int textID;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        final String patternNote = intent.getStringExtra("content");

        String idFromBundle = intent.getStringExtra("id");
        textID = Integer.parseInt((idFromBundle));

        final TextView textView = findViewById(R.id.noteText);

        final EditText editView = findViewById(R.id.noteEdit);

        textView.setText(patternNote);

        final PatternNoteViewModel mPatternViewModel = ViewModelProviders.of(this).get(PatternNoteViewModel.class);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editing){
                    setEditState(textView, editView, patternNote);
                }
                else {
                    UpdatePatternNoteAsyncTask test = new UpdatePatternNoteAsyncTask(textID, mPatternViewModel, context);
                    test.execute();
                    editing = false;
                }
            }
        });
    }

    public void setEditState(TextView textView, EditText editView, String patternNote){
        editing = true;
        editView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        editView.setText(patternNote);
    }

    private static class UpdatePatternNoteAsyncTask extends AsyncTask<PatternNote, Void, Void> {

        int textID;
        Context myCon;
        boolean errorToShow = false;
    PatternNoteViewModel test;
        public UpdatePatternNoteAsyncTask(int textID, PatternNoteViewModel m, Context con){
            this.textID = textID;
            myCon = con;

            test = m;
        }

        @Override
        protected Void doInBackground(final PatternNote... patternNote) {
            PatternNote noteToUpdate = test.getPatternNote(textID);
            if(noteToUpdate == null){
                errorToShow = true;
            }
            else {
                test.update(noteToUpdate);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(errorToShow){
                Toast errorMessage = Toast.makeText(myCon, "note is null", Toast.LENGTH_LONG);
                errorMessage.show();
            }
        }
    }
}

