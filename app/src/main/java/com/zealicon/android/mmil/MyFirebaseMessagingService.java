package com.zealicon.android.mmil;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.zealicon.android.mmil.activity.MainActivity;

import net.grandcentrix.tray.AppPreferences;

import java.util.Map;

/**
 * Created by root on 23/6/16.
 */
public class MyFirebaseMessagingService
extends FirebaseMessagingService{

    String TAG=getClass().getSimpleName();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
//        Log.v("NotificationReceived",remoteMessage.getNotification().getBody());
//        Log.v("NotificationReceived",remoteMessage.getData().toString());
     //   Toast.makeText(MyFirebaseMessagingService.this, "hakhgfgh", Toast.LENGTH_SHORT).show();
//        // If the application is in the foreground handle both data and notification messages here.
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.
//
//        Log.v(TAG, "From: " + remoteMessage.getFrom());
        Map<String,String> values=remoteMessage.getData();


        String notification=values.get("notification");

        if(notification.contentEquals("no"))
        {
            AppPreferences appPreferences = new AppPreferences(getApplicationContext()); // this Preference comes for free from the library
// save a key value pair

            appPreferences.put("firsttime", 0);
        }
        else {
//        Log.v(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("Zealicon")
                    .setContentText(values.get("text"))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
////        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("aditya",0);
////
////        sharedPreferences.edit().putString("image",remoteMessage.getNotification().getBody()).commit();
//

//
//// read the value for your key. the second parameter is a fallback (optional otherwise throws)
//
//
            notificationManager.notify(0, notificationBuilder.build());
        }
    }


}
