package com.zeal.android.mmil.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.zeal.android.mmil.R;
import com.zeal.android.mmil.Utils.URL1;
import com.zeal.android.mmil.connectionutils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class Register extends AppCompatActivity {

    EditText nameView, emailView, collegeView, contactView;
    Button register;
    Spinner yearView, branchView, courseView;

    private static String url;
    private static String TAG = "MyApp - Connection";
    private static String result;


    String name, email, college, contact, year, branch, course, token_id;

    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameView = (EditText) findViewById(R.id.name);
        emailView = (EditText) findViewById(R.id.email);
        collegeView = (EditText) findViewById(R.id.college);
        contactView = (EditText) findViewById(R.id.contact);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameView.getText().toString();
                email = emailView.getText().toString();
                college = collegeView.getText().toString();
                contact = contactView.getText().toString();
                course = courseView.getSelectedItem().toString();
                branch = branchView.getSelectedItem().toString();
                year = yearView.getSelectedItem().toString();

                Log.v(TAG, name + email + contact + year + branch);

                if (name.equals("") || email.equals("") || college.equals("") || contact.equals("") || course.equals("") || branch.equals("") || year.equals(""))
                    Toast.makeText(Register.this, "Sorry..Please Enter All Fields", Toast.LENGTH_SHORT).show();
                else {
                    Connection1 connection1 = new Connection1();
                    connection1.execute();
                }
            }
        });

        courseView = (Spinner) findViewById(R.id.course);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.course, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseView.setAdapter(adapter1);

        branchView = (Spinner) findViewById(R.id.branch);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.branch, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchView.setAdapter(adapter2);

        yearView = (Spinner) findViewById(R.id.year);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearView.setAdapter(adapter3);
    }
    void afterResult(String response)
    {
        try {
            JSONObject jsonObject=new JSONObject(response);
            String success=jsonObject.getString("status");
            if(success.equals("0"))
                Toast.makeText(Register.this,"There is some error",Toast.LENGTH_SHORT).show();
            else if(success.equals("2"))
                Toast.makeText(Register.this,"EmailID or Contact already Registered",Toast.LENGTH_SHORT).show();
            else {
                String zeal_id = jsonObject.getString("zeal_id");
                getSharedPreferences("zealiconid",0).edit().putString("zealid",zeal_id).apply();
                Intent intent=new Intent(getApplicationContext(),ZealId.class);

                //Toast.makeText(Register.this,"Successfully Registered Zealicon id "+zeal_id,Toast.LENGTH_SHORT).show();
                Log.v(TAG, zeal_id);
                startActivity(intent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public class Connection1 extends AsyncTask<Void, Void, String> {
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        String error;
        Connection1() {

        }
        @Override
        protected String doInBackground(Void... params) {
            try
            {
                StringBuilder postData=new StringBuilder();
                postData.append(URL1.getRegisterURL()+"?");
                //Iterator<String> paramiterator=param.keySet().iterator();

                postData.append(URLEncoder.encode("name", "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(name, "UTF-8"));

                if (postData.length() != 0)
                    postData.append('&');
                postData.append(URLEncoder.encode("email", "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(email, "UTF-8"));

                if(postData.length()!=0)
                    postData.append('&');
                postData.append(URLEncoder.encode("college", "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(college,"UTF-8"));


                if(postData.length()!=0)
                    postData.append('&');
                postData.append(URLEncoder.encode("contact", "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(contact,"UTF-8"));


                if(postData.length()!=0)
                    postData.append('&');
                postData.append(URLEncoder.encode("course", "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(course,"UTF-8"));

                if(postData.length()!=0)
                    postData.append('&');
                postData.append(URLEncoder.encode("branch", "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(branch,"UTF-8"));

                if(postData.length()!=0)
                    postData.append('&');
                postData.append(URLEncoder.encode("year", "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(year,"UTF-8"));

                if(postData.length()!=0)
                    postData.append('&');
                postData.append(URLEncoder.encode("app", "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode("1","UTF-8"));


                //Log.v(TAG,param.toString());
                URL url=new URL(postData.toString());
                Log.v(TAG, "get url " + postData.toString());
                //byte[] postDataBytes=postData.toString().getBytes("UTF-8");
                conn=(HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");

                InputStream inputStream = conn.getInputStream();

                StringBuffer buffer = new StringBuffer();
                if(inputStream==null){
                    return "null_inputstream";
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line ;

                while ( (line=bufferedReader.readLine())!=null ){
                    buffer.append(line + '\n');
                }

                if (buffer.length() == 0) {
                    return "null_inputstream";
                }

                String stringJSON = buffer.toString();
                Log.v("MyApp", "JSON retured in Attendance" + stringJSON);
                return stringJSON;

            } catch (UnknownHostException | ConnectException e) {
                error = "null_internet" ;
                e.printStackTrace();
            } catch (IOException e) {
                error= "null_file";
                e.printStackTrace();
            } finally {
                if ( conn!= null) {
                    conn.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (final IOException e) {
//                        Log.e(LOG_CAT, "ErrorClosingStream", e);
                    }
                }
            }

            return error;
        }
        @Override
        protected void onPostExecute(final String success) {
            afterResult(success);
            Log.v(TAG,success);
        }
    }
}