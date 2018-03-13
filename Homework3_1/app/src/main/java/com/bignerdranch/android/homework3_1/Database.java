package com.bignerdranch.android.homework3_1;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Owner on 2/5/2018.
 */

public class Database implements Serializable
{
    private List<FavoritePage> pages;
    private static Database instance = null;


    private Database() {
        pages = new ArrayList<FavoritePage>();
        pages.add(new FavoritePage("Amazon", "https://www.amazon.com/", 0));
        pages.add(new FavoritePage("Stack Overflow", "https://stackoverflow.com/", 0));
        pages.add(new FavoritePage("Westminster", "http://www.westminster.edu/index.cfm", 0));
        pages.add(new FavoritePage("Reddit", "https://www.reddit.com/", 0));
        pages.add(new FavoritePage("Evike", "http://www.evike.com/", 0));
        pages.add(new FavoritePage("Blizzard", "https://www.blizzard.com/en-us/", 0));
        pages.add(new FavoritePage("Android", "https://developer.android.com/index.html", 0));
        pages.add(new FavoritePage("Google", "        https://www.google.com/", 0));

    }

    public static Database getInstance()
    {
        if(instance == null)
            instance = new Database();

        return instance;
    }

    static void load(File f) throws IOException, ClassNotFoundException
    {
        FileInputStream fos = new FileInputStream(f);
        ObjectInputStream oos = new ObjectInputStream(fos);
        instance = (Database)oos.readObject();
        oos.close();
    }

    public void save(File f) throws  IOException
    {
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
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

    public static void loadDatabase(Context context) {
        File data = new File(context.getFilesDir(), "temp");

        try {
            load(data);
        } catch (IOException e) {
            Log.d("Broke", "New database: "+e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.d("COUNTER", "Data file corrupted (class not found): "+e.getMessage());
        }
    }

    public static void saveDatabase(Context context) {
        File data = new File(context.getFilesDir(), "temp");

        try {
            getInstance().save(data);
        } catch (IOException e) {
        }
    }

}
