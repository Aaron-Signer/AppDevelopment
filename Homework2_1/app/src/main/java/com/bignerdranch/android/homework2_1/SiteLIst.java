package com.bignerdranch.android.homework2_1;

import java.io.File;
import java.io.IOException;
import  java.util.*;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SiteLIst extends AppCompatActivity {

    Button[] buttons = new Button[9];
    Database favorites;
    File data;
    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_list);

        loadDatabase();
        if(favorites == null)
            favorites = Database.getInstance();


        buttons[0] = findViewById(R.id.b1);
        buttons[1] = findViewById(R.id.b2);
        buttons[2] = findViewById(R.id.b3);
        buttons[3] = findViewById(R.id.b4);
        buttons[4] = findViewById(R.id.b5);
        buttons[5] = findViewById(R.id.b6);
        buttons[6] = findViewById(R.id.b7);
        buttons[7] = findViewById(R.id.b8);

        for(int i = 0; i < favorites.getPageCount(); i++)
            buttons[i].setText(favorites.getPage(i).getName());

         for(int i = favorites.getPageCount(); i < 8; i++)
             buttons[i].setVisibility(View.INVISIBLE);

        for(int i = 0; i < favorites.getPageCount(); i++) {
            final int ii = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favorites.getPage(ii).inc();
                    Intent intent = SiteDetail.newIntent(SiteLIst.this, favorites.getPage(ii).getName(),
                            favorites.getPage(ii).getUrl(), Integer.toString(favorites.getPage(ii).getVisitCount()));
                    saveDatabase();
                    startActivity(intent);
                }
            });
        }
    }

    private void loadDatabase() {
        data = new File(getFilesDir(), "temp");

        try {
            favorites.load(data);
        } catch (IOException e) {
            Log.d("Broke", "New database: "+e.getMessage());
            favorites = Database.getInstance();
        } catch (ClassNotFoundException e) {
            Log.d("COUNTER", "Data file corrupted (class not found): "+e.getMessage());
            favorites = Database.getInstance();
        }
    }

    private void saveDatabase() {
        data = new File(getFilesDir(), "temp");

        try {
            favorites.save(data);
        }
        catch (IOException e) {
        }
    }


}
