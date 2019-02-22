package com.example.fsrmobileapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainPageAdapter mainPageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Main.onCreate:Starting.");
        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());

        //Set up the mainPageAdapter with the sections adapter
        viewPager = (ViewPager) findViewById(R.id.container);
        setupMainPageAdapter(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Log.d(TAG, "Main.onCreate:Ending.");
    }

    private void setupMainPageAdapter(ViewPager viewPager){
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabSiteFragment(), "Site");
        adapter.addFragment(new TabPendingFragment(), "Pending");
        adapter.addFragment(new TabSettingsFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }

    public void redirectLogin(View v) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
