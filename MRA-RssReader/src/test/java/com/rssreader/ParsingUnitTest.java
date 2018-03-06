package com.rssreader;

import org.json.JSONArray;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ParsingUnitTest {

    @Test
    public void testValidSingleItem() throws Exception {
        // Setup
        RssReader reader = new RssReaderImpl();

        InputStream resource = getClass().getClassLoader().getResourceAsStream("assets/validRssSingleItem");
        JSONArray items = reader.getFromRssFeed("http://bulbanews.bulbagarden.net/feed/news.rss");

        // Verify
        assertEquals(1, items.length());
    }

    private String convert(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

//    @Test
//    public void testValidMultipleItems() throws Exception {
//        // Setup
//        RssReader reader = new RssReaderImpl();
//        List<ParsedItem> items = reader.getFromRssFeed("src/main/java/com/rssreader/test/resource/validRssMultipleItem.xml");
//
//        // Verify
//        assertEquals(2, items.size());
//    }
//
//    @Test
//    public void testValidMultipleMixedItems() throws Exception {
//        // Setup
//        RssReader reader = new RssReaderImpl();
//        List<ParsedItem> items = reader.getFromRssFeed("src/main/java/com/rssreader/test/resource/validRssMultipleItemMixed.xml");
//
//        // Verify
//        assertEquals(3, items.size());
//    }
//
//    @Test
//    public void testValidInternalMultipleMixedItems() throws Exception {
//        // Setup
//        RssReader reader = new RssReaderImpl();
//        List<ParsedItem> items = reader.getFromRssFeed("src/main/java/com/rssreader/test/resource/validRssInternalItems.xml");
//
//        // Verify
//        assertEquals(2, items.size());
//
//        ParsedItem item = items.get(0);
//        assertEquals("First Pokémon GO patch released for iOS: Resolves Google account concerns", item.getTitle());
//        assertEquals("The first update for Pokémon GO is now available, but only for iOS.", item.getDescription());
//        assertEquals(null, item.getImageUrl());
//        assertEquals("http://bulbanews.bulbagarden.net/wiki/First_Pok%C3%A9mon_GO_patch_released_for_iOS", item.getLink());
//        assertEquals("13 Jul 2016", item.getPubDate());
//
//        item = items.get(1);
//        assertEquals("#Pokemon20: Trainer Andy Mientus", item.getTitle());
//        assertEquals("test", item.getDescription());
//        assertEquals("https://i2.ytimg.com/vi/qdBHGJk4ZN8/hqdefault.jpg", item.getImageUrl());
//        assertEquals("http://www.youtube.com/watch?v=qdBHGJk4ZN8", item.getLink());
//        assertEquals("2016-07-11T21:46:22+00:00", item.getPubDate());
//    }
//
//    @Test (expected = SAXParseException.class)
//    public void testInvalidItems() throws Exception {
//        new RssReaderImpl().getFromRssFeed("src/main/java/com/rssreader/test/resource/invalidParsedItem.xml");
//    }
}