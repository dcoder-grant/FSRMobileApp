package com.example.fsrmobileapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TabSettingsFragment extends Fragment {
    private static final String TAG = "TabSettingsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "SettingsFrag.onCreate:Starting.");
        View view = inflater.inflate(R.layout.fragment_tab_settings,container,false);
        Log.d(TAG, "SettingsFrag.onCreate:Ending.");
        return view;
    }
}
