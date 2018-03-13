package com.bignerdranch.android.homework3_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Owner on 3/6/2018.
 */

public class SiteDetailActivity extends AppCompatActivity{

    public static Intent newIntent(Context packageContext, int index)
    {
        Intent intent = new Intent(packageContext, SiteDetailActivity.class);
        intent.putExtra(SiteDetailFragment.ARG_FAVORITE_PAGE, index);
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
            Log.d("TESTINGPAGE", "null");
            fragment = SiteDetailFragment.newInstance(getIntent().getIntExtra(SiteDetailFragment.ARG_FAVORITE_PAGE,0));
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }
}
