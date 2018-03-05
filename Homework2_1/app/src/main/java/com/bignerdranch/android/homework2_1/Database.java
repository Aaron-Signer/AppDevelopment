package com.bignerdranch.android.homework2_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
        pages.add(new FavoritePage("Westminster.edu", "http://www.westminster.edu/index.cfm", 0));
        pages.add(new FavoritePage("Reddit", "https://www.reddit.com/", 0));
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

    void save(File f) throws  IOException
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


}
