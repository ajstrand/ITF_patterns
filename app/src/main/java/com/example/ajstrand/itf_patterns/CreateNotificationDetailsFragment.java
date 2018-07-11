package com.example.ajstrand.itf_patterns;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.ajstrand.itf_patterns.NotificationPublisher.NOTIFICATION_CHANNEL_ID;

/**
 * A fragment for sending notifications
 */
public class CreateNotificationDetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Context context;

    Class<NotificationPublisher> np = NotificationPublisher.class;

    List<ITF_Pattern.PatternItem> foo = ITF_Pattern.ITEMS;

    ArrayList<String> fooBar = new ArrayList<>();

    String note, pattern_name;

    /**
     * @method sendNotification
     */
    public void sendNotification() {
        Toast.makeText(getContext(), "sending notification about " + pattern_name, Toast.LENGTH_SHORT).show();
        Notification myNot = getNotification(note);
        scheduleNotification(myNot, 6000);
    }

    private void scheduleNotification(Notification notification, int delay) {
        Intent notificationIntent = new Intent(context, np);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        if (context == null) {
            throw new Error("context is null, please check the value " + content);
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
            builder.setContentTitle("Scheduled Notification");
            builder.setContentText(content);
            builder.setSmallIcon(R.drawable.ic_launcher);
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            return builder.build();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View local_view = inflater.inflate(R.layout.fragment_create_notification_details, container, false);
        Button testButton;
        testButton = local_view.findViewById(R.id.myid);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        createSpinner(local_view);
        return local_view;
    }

    private void createSpinner(View local_view) {
        for (int i = 0; i < foo.size(); i++) {
            ITF_Pattern.PatternItem test = foo.get(i);
            fooBar.add(test.title);
        }

        Spinner spinner = local_view.findViewById(R.id.my_spinner);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter adapter;
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, fooBar);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void onAttach(Context con) {
        super.onAttach(con);
        context = con;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        note = "dont forget to pratice " + parent.getItemAtPosition(pos).toString();
        pattern_name = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
