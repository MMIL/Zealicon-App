package com.yalantis.android.mmil.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yalantis.android.mmil.R;

/**
 * Created by my hp on 3/31/2016.
 */
public class ContactUs extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.contact, container, false);
        settexts(v);

        v.findViewById(R.id.button_so).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("http://zealicon.in/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        v.findViewById(R.id.button_su).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("http://jssmmil.herokuapp.com/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        v.findViewById(R.id.findusjss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:28.6144,77.3588?z=16");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        return v;
    }

    private void settexts(View v) {
        TextView tv1=(TextView)v.findViewById(R.id.textView_contactus);
        TextView tv2=(TextView)v.findViewById(R.id.textView_cant_afford);
        TextView tv3=(TextView)v.findViewById(R.id.textView_zkansari);
        TextView tv4=(TextView)v.findViewById(R.id.textView_chairman);
        TextView tv5=(TextView)v.findViewById(R.id.textView_chairman_phone);
        TextView tv6=(TextView)v.findViewById(R.id.textView_anantgarg);
        TextView tv7=(TextView)v.findViewById(R.id.textView_festivalsecretary);
        TextView tv8=(TextView)v.findViewById(R.id.textView_festivalsecretary_phone);
        TextView tv9=(TextView)v.findViewById(R.id.textView_nikhilverma);
        TextView tv10=(TextView)v.findViewById(R.id.textView_technical_coordinator);
        TextView tv11=(TextView)v.findViewById(R.id.textView_technical_coordinator_phone);
        TextView tv12=(TextView)v.findViewById(R.id.weblinks);
        TextView tv13=(TextView)v.findViewById(R.id.findus);
      //  setfontstyle(tv1);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "huggable.ttf");
        tv1.setTypeface(custom_font);
        setfontstyle(tv2);
        setfontstyle(tv3);
        setfontstyle(tv4);
        setfontstyle(tv5);
        setfontstyle(tv6);
        setfontstyle(tv7);
        setfontstyle(tv8);
        setfontstyle(tv9);
        setfontstyle(tv10);
        setfontstyle(tv11);
        setfontstyle(tv12);
        setfontstyle(tv13);

    }

    private void setfontstyle(TextView tv1) {
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "akbar.ttf");
        tv1.setTypeface(custom_font);
    }
}
