package com.bignerdranch.android.homework3_1;

import java.io.Serializable;

/**
 * Created by Owner on 2/5/2018.
 */

public class FavoritePage implements Serializable
{

    private String name;
    private String url;
    private int visitCount;

    public FavoritePage(String name, String url, int visitCount)
    {
        this.name = name;
        this.url = url;
        this.visitCount = visitCount;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public void inc()
    {
        visitCount++;
    }

}
