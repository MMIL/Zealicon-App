package com.zeal.android.mmil.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zeal.android.mmil.R;

/**
 * Created by my hp on 4/4/2016.
 */
public class Retry extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retry);
        ImageView im=(ImageView)findViewById(R.id.imageView3);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),SplashScreen.class);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
