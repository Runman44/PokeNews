package com.rssreader;

import org.json.JSONArray;

public interface RssReader {

    JSONArray getFromYoutube(String url) throws Exception;

    JSONArray getFromRssFeed(String url) throws Exception;

}
