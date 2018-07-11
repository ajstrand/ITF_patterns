package com.example.ajstrand.itf_patterns;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CreateNotificationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            CreateNotificationDetailsFragment fragment = new CreateNotificationDetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().
                    add(R.id.ttt, new CreateNotificationDetailsFragment()).commit();
        }




    }



}
