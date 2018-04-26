package com.example.ajstrand.itf_patterns;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class AddWordsUI<T extends ViewModel> extends AppCompatActivity implements LifecycleOwner {

    private PatternNoteViewModel mPatternViewModel;


    public static final int NEW_PATTERN_ACTIVITY_REQUEST_CODE = 1;

    private RecyclerView mRecyclerView;
    private NotesList adapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words_ui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        adapter = new NotesList(this);
        mRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddWordsUI.this, NewPatternNote.class);
                startActivityForResult(intent, NEW_PATTERN_ACTIVITY_REQUEST_CODE);
            }
        });

        mPatternViewModel = ViewModelProviders.of(this).get(PatternNoteViewModel.class);



        mPatternViewModel.getPatternNotes().observe(this, new Observer<List<PatternNote>>() {
            @Override
            public void onChanged(@Nullable final List<PatternNote> notes) {
                // Update the cached copy of the words in the adapter.
                    adapter.setPatternNotes(notes);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PATTERN_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            PatternNote word = new PatternNote(0, data.getStringExtra("title"), data.getStringExtra("content"));
            mPatternViewModel.insert(word);
            Toast.makeText(getApplicationContext(), word.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
