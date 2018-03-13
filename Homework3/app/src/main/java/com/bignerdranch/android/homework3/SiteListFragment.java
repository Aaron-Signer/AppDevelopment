package com.bignerdranch.android.homework3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

/**
 * Created by Owner on 2/20/2018.
 */

public class SiteListFragment extends Fragment {

    Button[] buttons = new Button[9];
    Database favorites;
    File data;
    TextView view;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        loadDatabase();
        if(favorites == null)
            favorites = Database.getInstance();

        buttons[0] = v.findViewById(R.id.b1);
        buttons[1] = v.findViewById(R.id.b2);
        buttons[2] = v.findViewById(R.id.b3);
        buttons[3] = v.findViewById(R.id.b4);
        buttons[4] = v.findViewById(R.id.b5);
        buttons[5] = v.findViewById(R.id.b6);
        buttons[6] = v.findViewById(R.id.b7);
        buttons[7] = v.findViewById(R.id.b8);

        for (int i = 0; i < favorites.getPageCount(); i++)
            buttons[i].setText(favorites.getPage(i).getName());

        for (int i = favorites.getPageCount(); i < 8; i++)
            buttons[i].setVisibility(View.INVISIBLE);

        for (int i = 0; i < favorites.getPageCount(); i++) {
            final int ii = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favorites.getPage(ii).inc();
                    Intent intent = SiteDetailActivity.newIntent(getActivity(), favorites.getPage(ii).getName(),
                            favorites.getPage(ii).getUrl(), Integer.toString(favorites.getPage(ii).getVisitCount()));
                    saveDatabase();
                    startActivity(intent);
                }
            });


        }
        return v;
    }
    private void loadDatabase() {
        data = new File(getContext().getFilesDir(), "temp");

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
        data = new File(getContext().getFilesDir(), "temp");

        try {
            favorites.save(data);
        } catch (IOException e) {
        }
    }
}
