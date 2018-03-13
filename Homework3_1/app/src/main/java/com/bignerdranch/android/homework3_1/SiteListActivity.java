package com.bignerdranch.android.homework3_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class SiteListActivity extends AppCompatActivity implements ItemShowable{

    private boolean landscape;
    private int curIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        if(savedInstanceState != null)
            curIndex = savedInstanceState.getInt(SiteDetailFragment.ARG_FAVORITE_PAGE, 0);

        landscape = findViewById(R.id.fragment_container_left) != null;
        if(!landscape)
            onCreatePortrait();
        else
            onCreateLandscape();

    }

    private void onCreatePortrait()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null)
        {
            fragment = new SiteListFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    private void onCreateLandscape()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_left);

        if(fragment == null)
        {
            fragment = new SiteListFragment();
            fm.beginTransaction().add(R.id.fragment_container_left, fragment).commit();
        }

        fragment = fm.findFragmentById(R.id.fragment_container_right);

        if(fragment == null)
        {
            fragment = SiteDetailFragment.newInstance(0);
            fm.beginTransaction().add(R.id.fragment_container_right, fragment).commit();
        }

        showItem(curIndex);

    }

    public void showItem(int index)
    {
        curIndex = index;
        Database.getInstance().getPage(index).inc();
        Database.saveDatabase(this);

        if(!landscape)
        {
            Intent intent = SiteDetailActivity.newIntent(this, index);
            startActivity(intent);
        }
        else
        {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.fragment_container);

            fragment = SiteDetailFragment.newInstance(index);
            fm.beginTransaction().replace(R.id.fragment_container_right, fragment).commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
//        Log.i(TAG\\, "onSaveInstanceState");
        savedInstanceState.putInt(SiteDetailFragment.ARG_FAVORITE_PAGE, curIndex);
    }

}
