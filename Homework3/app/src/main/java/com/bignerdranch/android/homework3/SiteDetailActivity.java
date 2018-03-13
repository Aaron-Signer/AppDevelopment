package com.bignerdranch.android.homework3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Owner on 3/6/2018.
 */

public class SiteDetailActivity extends AppCompatActivity{

    public static Intent newIntent(Context packageContext, String name, String url, String visits)
    {
        String [] info = {name, url, visits};
        Intent intent = new Intent(packageContext, SiteDetailActivity.class);
        intent.putExtra("stuff", info);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null)
        {
            fragment = new SiteDetailFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }
}
