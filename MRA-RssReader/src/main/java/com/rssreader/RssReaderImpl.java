package com.rssreader;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rssreader.youtube.Items;
import com.rssreader.youtube.Youtube;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class RssReaderImpl implements RssReader {

    @Override
    public JSONArray getFromYoutube(String url) throws Exception {
        //TODO sucky classes..
        JSONObject json = new JSONObject(readUrl(url));
        Gson gson = new Gson();
        Youtube mcArray = gson.fromJson(json.toString(), Youtube.class);
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
    public JSONArray getFromRssFeed(String feed) throws Exception {
        Gson gson = new Gson();
        String s = readUrl(feed);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();


        RssHandler rh = new RssHandler();
        sp.parse(new InputSource(new StringReader(s)), rh);

        String element = gson.toJson(
                rh.getParsedItemList(),
                new TypeToken<ArrayList<ParsedItem>>() {}.getType());
        return new JSONArray(element);
    }

    //TODO dont do this.
    //TODO OkHttp
    private static String readUrl(String urlString) throws Exception {
            URL url = new URL(urlString);
            URLConnection uc = url.openConnection();

            InputStream is = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();

            while((line = br.readLine()) != null){
                response.append(line);
                response.append('\n');

            }

            return response.toString();
    }
}
