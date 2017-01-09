package com.rssreader;

public class Id
{
    private String videoId;

    public String getVideoId ()
    {
        return videoId;
    }

    public void setVideoId (String videoId)
    {
        this.videoId = videoId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [videoId = "+videoId+"]";
    }
}
