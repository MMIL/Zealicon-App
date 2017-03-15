package com.zeal.android.mmil.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zeal.android.mmil.R;
import com.zeal.android.mmil.service.UpdateService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by my hp on 3/30/2016.
 */
public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        SharedPreferences sf=getSharedPreferences("firsttime",0);
        int i= sf.getInt("first",0);
        if(i==0) {
            requestjson();
        }
        else
        {
            Intent in=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(in);
        }
    }

    private void requestjson() {
     //   startalarmmanager();
        final String url="http://backoffice.zealicon.in/events/";

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
       //Coderz
       final StringRequest stringRequestcoderz = new StringRequest(Request.Method.GET, url+"2",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("MyApp", response);
                        SharedPreferences s=getSharedPreferences("events",0);
                        s.edit().putString("coderz",response).apply();
                        SharedPreferences sf=getSharedPreferences("firsttime",0);
                        sf.edit().putInt("first", 1).apply();
                        Intent in =new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(in);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.v("MyApp",error.toString());
                        Intent in=new Intent(getApplicationContext(),Retry.class);
                        startActivity(in);
                    }
                });
//Playiton
       final StringRequest stringRequestplayiton = new StringRequest(Request.Method.GET, url+"1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("MyApp", response);
                        SharedPreferences s=getSharedPreferences("events",0);
                        s.edit().putString("playiton",response).apply();
                        requestQueue.add(stringRequestcoderz);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.v("MyApp", error.toString());

                        Intent in=new Intent(getApplicationContext(),Retry.class);
                        startActivity(in);
                    }
                });
//Mechavoltz
        final StringRequest stringRequestmechavoltz = new StringRequest(Request.Method.GET, url+"3",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("MyApp", response);
                        SharedPreferences s=getSharedPreferences("events",0);
                        s.edit().putString("mechavoltz",response).apply();
                        requestQueue.add(stringRequestplayiton);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.v("MyApp",error.toString());
                        Intent in=new Intent(getApplicationContext(),Retry.class);
                        startActivity(in);
                    }
                });
//robotiles
        final StringRequest stringRequestrobotiles = new StringRequest(Request.Method.GET, url+"4",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("MyApp", response);
                        SharedPreferences s=getSharedPreferences("events",0);
                        s.edit().putString("robotiles",response).apply();
                        requestQueue.add(stringRequestmechavoltz);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.v("MyApp",error.toString());
                        Intent in=new Intent(getApplicationContext(),Retry.class);
                        startActivity(in);
                    }
                });
        //coloralo
        final StringRequest stringRequestcoloralo = new StringRequest(Request.Method.GET, url+"6",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("MyApp", response);
                        SharedPreferences s=getSharedPreferences("events",0);
                        s.edit().putString("coloralo",response).apply();
                        requestQueue.add(stringRequestrobotiles);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.v("MyApp",error.toString());
                        Intent in=new Intent(getApplicationContext(),Retry.class);
                        startActivity(in);
                    }
                });
        //Z-Wars
      final StringRequest stringRequestzwars = new StringRequest(Request.Method.GET, url+"5",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("MyApp", response);
                        SharedPreferences s=getSharedPreferences("events",0);
                        s.edit().putString("zwars",response).apply();
                        requestQueue.add(stringRequestcoloralo);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.v("MyApp",error.toString());
                        Intent in=new Intent(getApplicationContext(),Retry.class);
                        startActivity(in);
                    }
                });
        //Adding the string request to the queue






        requestQueue.add(stringRequestzwars);

        int socketTimeout = 20000;//20 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequestcoderz.setRetryPolicy(policy);
        stringRequestplayiton.setRetryPolicy(policy);
        stringRequestmechavoltz.setRetryPolicy(policy);
        stringRequestrobotiles.setRetryPolicy(policy);
        stringRequestcoloralo.setRetryPolicy(policy);
        stringRequestzwars.setRetryPolicy(policy);

    }

    private void startalarmmanager() {
        String toParse = "2016-04-06 10:00:00"; // Results in "2-5-2012 20:43"
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // I assume d-M, you may refer to M-d for month-day instead.
        Date date = null; // You will need try/catch around this
        try {
            date = formatter.parse(toParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long millis = date.getTime();
        Intent intent=new Intent(getApplicationContext(), UpdateService.AlarmReceiverUpdate.class);



        PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(),100000,intent,PendingIntent.FLAG_ONE_SHOT);

        AlarmManager am=(AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,millis,pi);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
