package com.rssreader;

public class Thumbnails
{
    private High high;

    public High getHigh ()
    {
        return high;
    }

    public void setHigh (High high)
    {
        this.high = high;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [high = "+high+"]";
    }
}
