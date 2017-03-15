package com.rssreader;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rssreader.youtube.Items;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class RssReaderImpl implements RssReader {

    public RssReaderImpl() {
    }

    @Override
    public JSONArray getFromYoutube(String url) throws Exception {
        JSONObject json = new JSONObject(readUrl(url));
        Gson gson = new Gson();
        com.rssreader.youtube.Youtube mcArray = gson.fromJson(json.toString(), com.rssreader.youtube.Youtube.class);
        List<ParsedItem> items = new ArrayList<>();

        for (Items youtube : mcArray.getItems()) {
            ParsedItem item = new ParsedItem();
            item.setTitle(youtube.getSnippet().getTitle());
            item.setImageUrl(youtube.getSnippet().getThumbnails().getHigh().getUrl());
            item.setPubDate(youtube.getSnippet().getPublishedAt());
            item.setLink("https://www.youtube.com/watch?v=" + youtube.getId().getVideoId());
            items.add(item);
        }

        Collections.reverse(items);

        String element = gson.toJson(
                items,
                new TypeToken<ArrayList<ParsedItem>>() {}.getType());
        return new JSONArray(element);
    }

    @Override
    public JSONArray getFromRssFeed(String url) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RssHandler handler = new RssHandler();
        saxParser.parse(url, handler);

        Gson gson = new Gson();
        String element = gson.toJson(
                handler.getParsedItemList(),
                new TypeToken<ArrayList<ParsedItem>>() {}.getType());
        return new JSONArray(element);
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
