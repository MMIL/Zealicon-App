package com.yalantis.android.mmil.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yalantis.android.mmil.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by my hp on 4/5/2016.
 */
public class RegisterTry extends AppCompatActivity {

    EditText nameView,emailView,collegeView,contactView;
    Button register;
    Spinner yearView,branchView,courseView;
    String year="1",branch="CSE",course="btech";
    String yeararray[]={"1","2","3","4"};
    String brancharray[]={"CSE","IT","ME","ECE","EE","CE","IC","EEE"};
    String coursearray[]={"btech","mca","mba"};
    String name,email,college,contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        yeararray=getResources().getStringArray(R.array.year);
        brancharray=getResources().getStringArray(R.array.branch);

        nameView=(EditText) findViewById(R.id.name);
        emailView=(EditText) findViewById(R.id.email);
        collegeView=(EditText) findViewById(R.id.college);
        contactView=(EditText) findViewById(R.id.contact);
        register=(Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("App","started");
                name=((EditText)findViewById(R.id.name)).getText().toString();
                email=((EditText)findViewById(R.id.email)).getText().toString();
                college=((EditText)findViewById(R.id.college)).getText().toString();
                contact=((EditText)findViewById(R.id.contact)).getText().toString();
                registertask();
            }
        });

        courseView = (Spinner) findViewById(R.id.course);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.course, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseView.setAdapter(adapter1);
        courseView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
course=coursearray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        branchView = (Spinner) findViewById(R.id.branch);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.branch, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchView.setAdapter(adapter2);
        branchView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
branch=brancharray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearView = (Spinner) findViewById(R.id.year);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearView.setAdapter(adapter3);
        yearView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = yeararray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }




    void registertask()
    {
        String url = "http://zealicon.in/reg_app?name="+name+"&email="+email+"&college="+college+"&course="+course+"&branch="+branch+"&year="+year+"&contact="+contact+"&app=1";


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jo=new JSONObject(response);
                            if(jo.getInt("status")==1){
                                String zealid=jo.getString("zeal_id");



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.v("Success",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
Log.v("Failure",error.toString());
                    }
                });/* {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("name",name);
                params.put("email",email);
                params.put("college",college);
                params.put("course",course);
                params.put("branch",branch);
                params.put("year",year);
                params.put("contact",contact);
                params.put("app","1");

                //returning parameter
                return params;
            }
        };*/

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.v("App","requestsent");
        requestQueue.add(stringRequest);

    }
    }


