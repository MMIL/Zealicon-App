package com.zeal.android.mmil.fragments;

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

import com.zeal.android.mmil.R;

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
        TextView tv12=(TextView)v.findViewById(R.id.weblinks);
        TextView tv13=(TextView)v.findViewById(R.id.findus);
        TextView tv14=(TextView)v.findViewById(R.id.textView_prashant);
        TextView tv15=(TextView)v.findViewById(R.id.textView_convenor);
      //  setfontstyle(tv1);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "abaddon.ttf");
        tv1.setTypeface(custom_font);
        tv12.setTypeface(custom_font);
        tv13.setTypeface(custom_font);

        setfontstyle(tv2);
        setfontstyle(tv3);
        setfontstyle(tv4);
        setfontstyle(tv5);
        setfontstyle(tv14);
        setfontstyle(tv15);


    }

    private void setfontstyle(TextView tv1) {
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "got.ttf");
        tv1.setTypeface(custom_font);
    }
}
