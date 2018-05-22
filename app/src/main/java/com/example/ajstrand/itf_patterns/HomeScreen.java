package com.example.ajstrand.itf_patterns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    Context con = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button seeNotes = findViewById(R.id.seeNotes);
        Button seePatterns = findViewById(R.id.seePatterns);

        seeNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, AddWordsUI.class);
                con.startActivity(intent);
            }
        });

        seePatterns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, PatternListActivity.class);
                con.startActivity(intent);
            }
        });
    }

}
