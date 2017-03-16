package com.zealicon.android.mmil.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.zealicon.android.mmil.R;

/**
 * Created by my hp on 4/2/2016.
 */
public class NotificationService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationService(String name) {
        super(name);
    }
    public NotificationService(){
        super("hi");

    }

    @Override
    protected void onHandleIntent(Intent intent) {


// build notification
        Log.v("work","work");
        String eventname=intent.getStringExtra("eventname");
// the addAction re-use the same intent to keep the example short
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_profile)
                        .setContentTitle("Hurry up!")
                        .setContentText(eventname+" is going to start in 15 minutes.Make sure to be there.");
        int NOTIFICATION_ID = intent.getIntExtra("keynotify",1);
        Log.v("keynotify2",NOTIFICATION_ID+"");
       // SharedPreferences sf=getSharedPreferences("notify",0);
        //NOTIFICATION_ID=sf.getInt("key",1);

      //  Intent targetIntent = new Intent(this, MyFavoriteActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());
       // sf.edit().putInt("key",NOTIFICATION_ID+1).commit();
    }

    public static class AlarmReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            int notification=intent.getIntExtra("keynotify",1);
            String eventname=intent.getStringExtra("eventname");
            Intent sendIntent=new Intent(context,NotificationService.class);
            sendIntent.putExtra("keynotify",notification);
            sendIntent.putExtra("eventname",eventname);

            context.startService(sendIntent);
        }
    }

}
