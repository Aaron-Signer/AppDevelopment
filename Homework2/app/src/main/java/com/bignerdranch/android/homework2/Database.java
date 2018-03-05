package com.bignerdranch.android.homework2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Owner on 2/5/2018.
 */

public class Database
{

    private List<FavoritePage> pages;
    private static Database instance = null;


    private Database()
    {
        pages = new ArrayList<FavoritePage>();
        pages.add(new FavoritePage("Newegg", "www.newegg.com", 0));
    }

    public static Database getInstance()
    {
        if(instance == null)
            instance = new Database();

        return instance;
    }

    static void load(File f) throws IOException, ClassCastException
    {

    }

    void save(File f) throws  IOException
    {

    }

    public int getPageCount()
    {
        return pages.size();
    }

    public FavoritePage getPage(int i)
    {
        return pages.get(i);
    }

    public List<FavoritePage> getPages()
    {
        return Collections.unmodifiableList(pages);
    }


}
