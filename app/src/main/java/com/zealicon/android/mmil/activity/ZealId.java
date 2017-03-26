package com.zealicon.android.mmil.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zealicon.android.mmil.R;



/**
 * Created by my hp on 4/5/2016.
 */
public class ZealId extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zealid);
        TextView tv1=(TextView)findViewById(R.id.textView);
        TextView tv2=(TextView)findViewById(R.id.textView2);
        TextView tv4=(TextView)findViewById(R.id.textView4);
        TextView tv5=(TextView)findViewById(R.id.textView5);
        TextView tvzealid=(TextView)findViewById(R.id.tv_zealid);

        Typeface custom_font_extravaganza = Typeface.createFromAsset(getApplicationContext().getAssets(), "got.ttf");
        tv1.setTypeface(custom_font_extravaganza);
        tv2.setTypeface(custom_font_extravaganza);
        tv4.setTypeface(custom_font_extravaganza);
        tv5.setTypeface(custom_font_extravaganza);
        tvzealid.setTypeface(custom_font_extravaganza);
        String s=getSharedPreferences("zealiconid",0).getString("zealid","Sorry Try again");
        tvzealid.setText(s);

        findViewById(R.id.button_zealid_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
