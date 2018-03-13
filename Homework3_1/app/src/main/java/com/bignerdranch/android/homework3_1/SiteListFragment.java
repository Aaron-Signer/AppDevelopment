package com.bignerdranch.android.homework3_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Owner on 2/20/2018.
 */

public class SiteListFragment extends Fragment {

    private RecyclerView mListRecyclerView;
    private FavoriteAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        mListRecyclerView = v.findViewById(R.id.list_recycler_view);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Database.loadDatabase(getActivity());
        if(Database.getInstance() == null)
        {
            Log.d("DATABASE", "Creating new database");
            Database.getInstance();
        }

        updateUI();
        return v;
    }

    private class FavoriteHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mName;
        private int index = 0;

        public FavoriteHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.list_item_favorites, parent, false));
            itemView.setOnClickListener(this);
            mName = itemView.findViewById(R.id.website_name);
        }

        public void bind(int indx)
        {
            index = indx;
            mName.setText(Database.getInstance().getPage(indx).getName());
        }

        @Override
        public void onClick(View view)
        {
            Log.d("TESTINGPAGE", "In on click");
            ((ItemShowable)getActivity()).showItem(index);
        }

    }

    private class FavoriteAdapter extends RecyclerView.Adapter<FavoriteHolder>
    {
        private List<FavoritePage> mPages;

        public FavoriteAdapter(List<FavoritePage> pages)
        {
            mPages = pages;
        }

        @Override
        public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new FavoriteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(FavoriteHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return mPages.size();
        }
    }

    private void updateUI()
    {
        List<FavoritePage> crimes = Database.getInstance().getPages();

        mAdapter = new FavoriteAdapter(crimes);
        mListRecyclerView.setAdapter(mAdapter);
    }




}
