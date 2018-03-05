package com.bignerdranch.android.homework2_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class SiteDetail extends AppCompatActivity {

    TextView url;
    TextView visits;
    String [] stuff;

    WebView myWebView;

    public static Intent newIntent(Context packageContext, String name, String url, String visits)
    {
        String [] info = {name, url, visits};
        Intent intent = new Intent(packageContext,SiteDetail.class);
        intent.putExtra("stuff", info);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_detail);


        stuff = getIntent().getStringArrayExtra("stuff");
        url = findViewById(R.id.url);
        url.setText(stuff[1]);

        visits = findViewById(R.id.visits);
        visits.setText(stuff[2]);

        myWebView = findViewById(R.id.webview);
        myWebView.loadUrl(stuff[1]);
    }


}
