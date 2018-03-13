package com.bignerdranch.android.homework3_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


/**
 * Created by Owner on 2/20/2018.
 */

public class SiteDetailFragment extends Fragment {

    TextView url;
    TextView visits;
    String [] stuff;
    int mIndex = 0;

    WebView myWebView;

    public static final String ARG_FAVORITE_PAGE = "fav_page";

    public static SiteDetailFragment newInstance(int index)
    {
        Bundle args = new Bundle();
        args.putInt(ARG_FAVORITE_PAGE, index);

        SiteDetailFragment fragment = new SiteDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_site_detail, container, false);
        mIndex = getArguments().getInt(ARG_FAVORITE_PAGE);
//        if(savedInstanceState != null)
//            mIndex = savedInstanceState.getInt(ARG_FAVORITE_PAGE);

//        Log.d("TESTINGPAGE", v.toString());
        Log.d("TESTINGPAGE", Integer.toString(Database.getInstance().getPage(mIndex).getVisitCount()));

        url = v.findViewById(R.id.url);
        url.setText(Database.getInstance().getPage(mIndex).getUrl());

        visits = v.findViewById(R.id.visits);
        visits.setText(Integer.toString(Database.getInstance().getPage(mIndex).getVisitCount()));

        myWebView = v.findViewById(R.id.webview);
        myWebView.loadUrl(Database.getInstance().getPage(mIndex).getUrl());

        return v;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("TESTINGPAGE", "onSaveInstanceState");

        savedInstanceState.putInt(ARG_FAVORITE_PAGE, mIndex);
    }
}
