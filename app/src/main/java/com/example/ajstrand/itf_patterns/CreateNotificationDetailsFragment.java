package com.example.ajstrand.itf_patterns;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreateNotificationDetailsFragment extends Fragment {


    Context context;


    /**
     * @method sendNotification
     */
    public void sendNotification() {
        Toast.makeText(getContext(), "hiiiiiii", Toast.LENGTH_SHORT).show();
        Notification myNot = getNotification("helllllo");
        scheduleNotification(myNot, 6000);
    }

    private void scheduleNotification(Notification notification, int delay) {
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        if(context == null){
            throw new Error("context is null, please check the value " + content);
        }
        else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("Scheduled Notification");
            builder.setContentText(content);
            builder.setSmallIcon(R.drawable.ic_launcher);
            return builder.build();
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_notification_details, container, false);
        Button testButton;
        testButton = (Button) v.findViewById(R.id.myid);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
        return v;
    }

    public void onAttach(Context con){
    super.onAttach(con);
    context = con;
    }
}
