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

    TextView textView;
    EditText editView;
    TextView titleView;
    EditText titleEditView;

    String patternNoteTitle;
    String patternNote;
    String patternNoteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final PatternNoteViewModel mPatternViewModel = ViewModelProviders.of(this).get(PatternNoteViewModel.class);


        Intent intent = getIntent();

        patternNoteTitle = intent.getStringExtra("title");

        patternNote = intent.getStringExtra("content");

        patternNoteID = intent.getStringExtra("id");
        textID = Integer.parseInt((patternNoteID));

        textView = findViewById(R.id.noteText);

        editView = findViewById(R.id.noteEdit);

        titleView = findViewById(R.id.noteTitle);

        titleEditView = findViewById(R.id.titleEdit);
        textView.setText(patternNote);
        titleView.setText(patternNoteTitle);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editing){
                    setEditState();
                }
                else {
                    String editViewText = String.valueOf(editView.getText());
                    String titleEditText = String.valueOf(titleEditView.getText());
                    UpdatePatternNoteAsyncTask updateNoteContents = new UpdatePatternNoteAsyncTask(textID, mPatternViewModel, context, editViewText, titleEditText);
                    updateNoteContents.execute();
                    editing = false;
                }
            }
        });
    }

    public void setEditState(){
        editing = true;
        editView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        titleEditView.setVisibility(View.VISIBLE);
        titleView.setVisibility(View.INVISIBLE);
        editView.setText(patternNote);
        titleEditView.setText(patternNoteTitle);
    }

    private static class UpdatePatternNoteAsyncTask extends AsyncTask<PatternNote, Void, Void> {

        int textIDToGet;
        Context myCon;
        boolean errorToShow = false;
        PatternNoteViewModel noteViewModel;
        String patternNoteText;
        String patternNoteTitleText;

        public UpdatePatternNoteAsyncTask(int textID, PatternNoteViewModel viewmodel, Context con, String editViewText, String titleEditText) {
            this.textIDToGet = textID;
            myCon = con;
            patternNoteText = editViewText;
            noteViewModel = viewmodel;
            patternNoteTitleText = titleEditText;

        }
        @Override
        protected Void doInBackground(final PatternNote... patternNote) {
            PatternNote noteToUpdate = noteViewModel.getPatternNote(textIDToGet);
            if(noteToUpdate == null){
                errorToShow = true;
            }
            else {
                noteToUpdate.text = patternNoteText;
                noteToUpdate.name = patternNoteTitleText;
                noteViewModel.update(noteToUpdate);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(errorToShow){
                Toast errorMessage = Toast.makeText(myCon, "an error occurred. note may be null or some other type of error", Toast.LENGTH_LONG);
                errorMessage.show();
            }
            else {
                Toast message = Toast.makeText(myCon, "note updated", Toast.LENGTH_LONG);
                message.show();
            }
        }
    }
}

