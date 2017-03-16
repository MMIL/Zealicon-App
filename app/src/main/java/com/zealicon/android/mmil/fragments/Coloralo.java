package com.zealicon.android.mmil.fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zealicon.android.mmil.R;
import com.zealicon.android.mmil.widget.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by my hp on 4/1/2016.
 */
public class Coloralo extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ProgressDialog pd ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.events,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view_events);
        ImageView im=(ImageView)v.findViewById(R.id.image_events_superhero);
        im.setImageResource(R.drawable.coloralo1);
        LinearLayout l=(LinearLayout)v.findViewById(R.id.eventspage);
        pd = new ProgressDialog(getContext(),ProgressDialog.STYLE_SPINNER) ;
        pd.setMessage("RUKO AA RHA HAI");
        l.setBackgroundResource(R.drawable.background);
        TextView tv=(TextView)v.findViewById(R.id.categories_events);
        tv.setText("COLORALO");
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "abaddon.ttf");
        tv.setTypeface(custom_font);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        SharedPreferences sf=getActivity().getSharedPreferences("events",0);

        loaddata(sf.getString("coloralo","none"));
        return v;
    }

    private void loaddata(String string) {
        JSONArray ja= null;
        try {
            ja = new JSONArray(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> ar=new ArrayList<>();
        for(int i=0;i<ja.length();i++)
        {
            try {
                JSONObject jt=ja.getJSONObject(i);
                ar.add(jt.getString("event_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mAdapter = new MyAdapter(ar,getContext(),"coloralo",pd);
            // mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setAdapter(mAdapter);

        }
    }
    @Override
    public void onPause() {
        super.onPause();
        pd.hide();

    }
}

