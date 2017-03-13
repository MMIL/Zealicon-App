package com.yalantis.android.mmil.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yalantis.android.animation.GuillotineAnimation;
import com.yalantis.android.mmil.R;
import com.yalantis.android.mmil.fragments.Coloralo;
import com.yalantis.android.mmil.fragments.ContactUs;
import com.yalantis.android.mmil.fragments.Mechavoltz;
import com.yalantis.android.mmil.fragments.OurTeam;
import com.yalantis.android.mmil.fragments.Playiton;
import com.yalantis.android.mmil.fragments.Robotiles;
import com.yalantis.android.mmil.fragments.ZealiconMain;
import com.yalantis.android.mmil.fragments.Coderz;
import com.yalantis.android.mmil.fragments.Zwars;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Dmytro Denysenko on 5/4/15.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final long RIPPLE_DURATION = 250;


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.root)
    FrameLayout root;
    @InjectView(R.id.content_hamburger)
    View contentHamburger;

    GuillotineAnimation g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        ButterKnife.inject(this);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }
        TextView tv_extravaganza=(TextView)findViewById(R.id.zealicon_title);
        Typeface custom_font_extravaganza = Typeface.createFromAsset(getApplicationContext().getAssets(), "got.ttf");
        tv_extravaganza.setTypeface(custom_font_extravaganza);

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotinetry, null);
        guillotineMenu.findViewById(R.id.profile_group).setOnClickListener(this);
       // guillotineMenu.findViewById(R.id.feed_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.coderz_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.playiton_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.mechavoltz_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.robotiles_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.coloralo_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.zwars_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.settings_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.our_team).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.sponsors).setOnClickListener(this);


        guillotineMenu.findViewById(R.id.activity_group).setOnClickListener(this);
        root.addView(guillotineMenu);
        ZealiconMain p=new ZealiconMain();
        FragmentManager f=getSupportFragmentManager();
        f.beginTransaction().add(R.id.fragment_container,p).commit();
        g=new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_group:
            ZealiconMain n = new ZealiconMain();
            android.support.v4.app.FragmentTransaction i = getSupportFragmentManager().beginTransaction();
            i.replace(R.id.fragment_container, n);
            i.addToBackStack(null);
            i.commit();
            g.close();
                break;
            case R.id.coderz_group:
                Coderz p1 = new Coderz();
                android.support.v4.app.FragmentTransaction f1 = getSupportFragmentManager().beginTransaction();
                f1.replace(R.id.fragment_container, p1);
                f1.addToBackStack(null);
                f1.commit();
                g.close();
                break;
            case R.id.our_team:
                OurTeam our=new OurTeam();
                android.support.v4.app.FragmentTransaction four = getSupportFragmentManager().beginTransaction();
                four.replace(R.id.fragment_container, our);
                four.addToBackStack(null);
                four.commit();
                g.close();
                break;
            case R.id.playiton_group:
                Playiton p2 = new Playiton();
                android.support.v4.app.FragmentTransaction f2 = getSupportFragmentManager().beginTransaction();
                f2.replace(R.id.fragment_container, p2);
                f2.addToBackStack(null);
                f2.commit();
                g.close();
                break;
            case R.id.mechavoltz_group:
                Mechavoltz p3 = new Mechavoltz();
                android.support.v4.app.FragmentTransaction f3 = getSupportFragmentManager().beginTransaction();
                f3.replace(R.id.fragment_container, p3);
                f3.addToBackStack(null);
                f3.commit();
                g.close();
                break;
            case R.id.robotiles_group:
                Robotiles p4 = new Robotiles();
                android.support.v4.app.FragmentTransaction f4 = getSupportFragmentManager().beginTransaction();
                f4.replace(R.id.fragment_container, p4);
                f4.addToBackStack(null);
                f4.commit();
                g.close();
                break;
            case R.id.coloralo_group:
                Coloralo p5 = new Coloralo();
                android.support.v4.app.FragmentTransaction f5 = getSupportFragmentManager().beginTransaction();
                f5.replace(R.id.fragment_container, p5);
                f5.addToBackStack(null);
                f5.commit();
                g.close();
                break;
            case R.id.zwars_group:
                Zwars p6 = new Zwars();
                android.support.v4.app.FragmentTransaction f6 = getSupportFragmentManager().beginTransaction();
                f6.replace(R.id.fragment_container, p6);
                f6.addToBackStack(null);
                f6.commit();
                g.close();
                break;
            case R.id.activity_group:
                ContactUs l = new ContactUs();
                android.support.v4.app.FragmentTransaction j = getSupportFragmentManager().beginTransaction();
                j.replace(R.id.fragment_container, l);
                j.addToBackStack(null);
                j.commit();
                g.close();
                break;
            case R.id.settings_group:
                finish();
                break;
            case R.id.sponsors:
                Intent intent=new Intent(getApplicationContext(),Sponsors.class);
                startActivity(intent);
                break;

        }
    }


    }



