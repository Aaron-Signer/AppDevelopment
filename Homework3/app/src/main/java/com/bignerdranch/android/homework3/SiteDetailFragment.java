package com.bignerdranch.android.homework3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Owner on 2/20/2018.
 */

public class SiteDetailFragment extends Fragment {

    TextView url;
    TextView visits;
    String [] stuff;

    WebView myWebView;

    public static Intent newIntent(Context packageContext, String name, String url, String visits)
    {
        String [] info = {name, url, visits};
        Intent intent = new Intent(packageContext,SiteDetailFragment.class);
        intent.putExtra("stuff", info);
        return intent;
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

        stuff = getActivity().getIntent().getStringArrayExtra("stuff");
        url = v.findViewById(R.id.url);
        url.setText(stuff[1]);

        visits = v.findViewById(R.id.visits);
        visits.setText(stuff[2]);

        myWebView = v.findViewById(R.id.webview);
        myWebView.loadUrl(stuff[1]);

        return v;
    }
}
