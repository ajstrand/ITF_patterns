package com.example.ajstrand.itf_patterns;

/**
 * Created by ajstrand on 10/16/2017.
 */

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationPublisher extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    public static final String NOTIFICATION_CHANNEL_ID = "4655";

    //Notification Channel
    CharSequence channelName = "foo";
    int importance = NotificationManager.IMPORTANCE_LOW;
    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);


    public void onReceive(Context context, Intent intent) {


        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(id, notification);
        notificationManager.createNotificationChannel(notificationChannel);

    }
}
