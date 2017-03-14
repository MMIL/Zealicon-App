package com.yalantis.android.mmil.activity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.yalantis.android.mmil.R;
import com.yalantis.android.mmil.service.NotificationService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by my hp on 3/31/2016.
 */
public class EventDetails extends AppCompatActivity {
    String dateofevent="hello";
    String eventname;
    String pno="8860488735";
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventdetailsnew);
        Toolbar t = (Toolbar) findViewById(R.id.toolbar_eventdetails);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  Toast.makeText(EventDetails.this, "position" + getIntent().getExtras().getInt("pos", 0), Toast.LENGTH_SHORT).show();
        TextView name = (TextView) findViewById(R.id.text_event_details_name);
        TextView description = (TextView) findViewById(R.id.text_event_details_description);
        TextView first = (TextView) findViewById(R.id.text_event_details_firstprize);
        TextView second = (TextView) findViewById(R.id.text_event_details_secondprize);
        TextView contact = (TextView) findViewById(R.id.text_event_details_contact);
        findViewById(R.id.callbutton_eventdetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + pno));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
        SharedPreferences sf = getSharedPreferences("events", 0);
        int position = getIntent().getExtras().getInt("pos", 0);
        String token = getIntent().getExtras().getString("token", "coderz");
        try {
            JSONArray ja = new JSONArray(sf.getString(token, "coderz"));
            JSONObject jo = ja.getJSONObject(position);
            String contact_number = null;
            if (jo.getString("contact").equals("")) {
                contact_number = "No contacts";

            } else {
                JSONArray contact_array = new JSONArray(jo.getString("contact"));
                StringBuilder sb = new StringBuilder();
                pno=contact_array.getJSONObject(0).getString("number");
                for (int i = 0; i < contact_array.length(); i++) {
                    JSONObject contact_details = contact_array.getJSONObject(i);
                    sb.append(contact_details.getString("name") + " : ");
                    sb.append(contact_details.getString("number") + "\n");
                }
                contact_number = sb.toString();
            }
            Log.v("PRIZE_MONEY", jo.getString("prize_money"));
            String firstprize = "No Idea";
            String secondprize = "No Idea";
            if (jo.getString("prize_money").equals("null")) {
                firstprize = "No Idea";
                secondprize = "No Idea";
            } else {
                JSONArray prizearray = new JSONArray(jo.getString("prize_money"));
                firstprize = prizearray.getString(0);
                secondprize = prizearray.getString(1);
            }

eventname=jo.getString("event_name");
            String eventdescription=jo.getString("event_description");
            String longdescription=jo.getString("long_des");
            String rules=jo.getString("rules");
            JSONArray rulesarray=new JSONArray(rules);
            StringBuilder rulesbuilder=new StringBuilder();
            rulesbuilder.append("Rules:"+"\n");
            for(int i=0;i<rulesarray.length();i++)
            {
                rulesbuilder.append("  #"+rulesarray.get(i)+"\n");
            }
            rules=rulesbuilder.toString();
            String timing[]=jo.getString("timing").split(" ");
            eventdescription=Html.fromHtml(eventdescription).toString();
            longdescription=Html.fromHtml(longdescription).toString();
            timing[1]=timing[1].substring(0,5);

            String finaldescription=eventdescription+"\n"+longdescription+"\n"+rules+"\n"+"Date: "+timing[0]+"\n"+"Time: "+timing[1];
            name.setText(jo.getString("event_name"));
            //finaldescription=Html.fromHtml(finaldescription).toString();
            description.setText(finaldescription);
            if(jo.getString("timing").equals("null")||jo.getString("timing").equals(null))
            dateofevent="hello";
            else
            dateofevent=jo.getString("timing");
          //  Log.e("Event", Html.fromHtml(jo.getString("event_description")).toString());
            first.setText(firstprize);
            second.setText(secondprize);
            contact.setText(contact_number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SwitchCompat switchCompat=(SwitchCompat)findViewById(R.id.switchcompat);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked&&!dateofevent.equals("hello")) {
                                      //  notifyme();
                } else if(!dateofevent.equals("hello")){
                  //  canclenotifyme();
                }
            }
        });
    styleTexts();
    }

    private void canclenotifyme() {
        Intent intent = new Intent(this, NotificationService.AlarmReceiver.class);
        int id=getSharedPreferences("notify",0).getInt(eventname,1);
        PendingIntent sender = PendingIntent.getBroadcast(this,id , intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.cancel(sender);
    }

    private void notifyme() {
        String toParse = dateofevent; // Results in "2-5-2012 20:43"
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // I assume d-M, you may refer to M-d for month-day instead.
        Date date = null; // You will need try/catch around this
        try {
            date = formatter.parse(toParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SharedPreferences sf=getSharedPreferences("notify",0);
        int notificationid=sf.getInt("key",1);
        sf.edit().putInt(eventname,notificationid).commit();
        long millis = date.getTime();
        Intent intent=new Intent(getApplicationContext(), NotificationService.AlarmReceiver.class);
        intent.putExtra("keynotify", notificationid);
        intent.putExtra("eventname",eventname);
        Log.v("keynotify1", notificationid + "");


        PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(),notificationid,intent,PendingIntent.FLAG_ONE_SHOT);
        sf.edit().putInt("key",notificationid+1).commit();
        AlarmManager am=(AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,millis-900000,pi);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
               // NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
     private void styleTexts()
     {
         TextView v1=(TextView)findViewById(R.id.text_event_details_name);
         TextView v2=(TextView)findViewById(R.id.toggle_text);
         TextView v3=(TextView)findViewById(R.id.text_event_details_description);
         TextView v4=(TextView)findViewById(R.id.text_event_details_firstprize);
         TextView v5=(TextView)findViewById(R.id.text_event_details_secondprize);
         TextView v6=(TextView)findViewById(R.id.text_event_details_contact);
//         TextView v7=(TextView)findViewById(R.id.contact_text);
         TextView v8=(TextView)findViewById(R.id.call_text);

         Typeface custom_font = Typeface.createFromAsset(getApplicationContext().getAssets(), "got.ttf");
         v1.setTypeface(custom_font);
         settextstyle(v2);
         settextstyle(v3);
         settextstyle(v4);
         settextstyle(v5);
         settextstyle(v6);
//         settextstyle(v7);
         settextstyle(v8);
     }
    private void settextstyle(TextView tv1) {
        Typeface custom_font = Typeface.createFromAsset(getApplicationContext().getAssets(), "meph.ttf");
        tv1.setTypeface(custom_font);
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
