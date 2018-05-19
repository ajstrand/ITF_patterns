package com.example.ajstrand.itf_patterns;

import android.arch.lifecycle.LiveData;
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

import java.util.List;

public class ShowNote extends AppCompatActivity {

    boolean editing = false;

    int textID;

    private PatternNoteViewModel mPatternViewModel;


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

        mPatternViewModel = ViewModelProviders.of(this).get(PatternNoteViewModel.class);


        final TextView textView = findViewById(R.id.noteText);

        final EditText editView = findViewById(R.id.noteEdit);

        textView.setText(patternNote);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editing){
                    setEditState(textView, editView, patternNote);
                }
                else {
                    PatternNote noteToUpdate = mPatternViewModel.getPatternNote(textID);

                    mPatternViewModel.update(noteToUpdate);
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
}

