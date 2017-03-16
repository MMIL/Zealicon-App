package com.zealicon.android.mmil.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zealicon.android.mmil.R;

/**
 * Created by my hp on 4/3/2016.
 */
public class OurTeam extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.our_team,container,false);
        TextView tv1=(TextView)v.findViewById(R.id.tv_team);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "abaddon.ttf");
        tv1.setTypeface(custom_font);
        return v;
    }
}
