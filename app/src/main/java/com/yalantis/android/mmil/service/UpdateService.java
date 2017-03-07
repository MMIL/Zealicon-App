package com.yalantis.android.mmil.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;

import com.yalantis.android.mmil.R;

/**
 * Created by my hp on 4/4/2016.
 */
public class UpdateService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UpdateService(String name) {
        super(name);
    }
    public UpdateService(){
        super("yo");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sf=getSharedPreferences("firsttime",0);
        sf.edit().putInt("first", 0).apply();

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_profile)
                        .setContentTitle("Hurry up!")
                        .setContentText(" We have updates for Zealicon Events! Open the app!");
        PendingIntent contentIntent = PendingIntent.getActivity(this, 100000, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(100000, builder.build());
    }


    public static class AlarmReceiverUpdate extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {

            Intent sendIntent=new Intent(context,UpdateService.class);

            context.startService(sendIntent);
        }
    }

}
