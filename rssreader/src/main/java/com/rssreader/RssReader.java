package com.rssreader;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class RssReader {

    private String rssUrl;

    public RssReader(String url) {
        rssUrl = url;
    }

    public List<RssItem> getItems() throws Exception {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            RssHandler handler = new RssHandler();
            saxParser.parse(rssUrl, handler);
            return handler.getRssItemList();
        } catch (Exception ex) {
            JSONObject json2 = new JSONObject(readUrl(rssUrl));
            Gson gson = new Gson();
            Youtube mcArray = gson.fromJson(json2.toString(), Youtube.class);
            List<RssItem> items = new ArrayList<>();

            for (Items youtube : mcArray.getItems()) {
                RssItem item = new RssItem();
                item.setTitle(youtube.getSnippet().getTitle());
                item.setImageUrl(youtube.getSnippet().getThumbnails().getHigh().getUrl());
                item.setPubDate(youtube.getSnippet().getPublishedAt());
                item.setLink("https://www.youtube.com/watch?v=" + youtube.getId().getVideoId());
                items.add(item);
            }

            Collections.reverse(items);

            return items;


//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser saxParser = factory.newSAXParser();
//            RssHandler handler = new RssHandler();
//            saxParser.parse(inputSource, handler);
//            return handler.getRssItemList();
        }
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
