package com.rssreader.test;

import com.rssreader.RssItem;
import com.rssreader.RssReader;

import org.junit.Test;
import org.xml.sax.SAXParseException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParsingUnitTest {

    @Test
    public void testValidSingleItem() throws Exception {
        // Setup
        RssReader reader = new RssReader("src/main/java/com/rssreader/test/resource/validRssSingleItem.xml");
        List<RssItem> items = reader.getItems();

        // Verify
        assertEquals(1, items.size());
    }

    @Test
    public void testValidMultipleItems() throws Exception {
        // Setup
        RssReader reader = new RssReader("src/main/java/com/rssreader/test/resource/validRssMultipleItem.xml");
        List<RssItem> items = reader.getItems();

        // Verify
        assertEquals(2, items.size());
    }

    @Test
    public void testValidMultipleMixedItems() throws Exception {
        // Setup
        RssReader reader = new RssReader("src/main/java/com/rssreader/test/resource/validRssMultipleItemMixed.xml");
        List<RssItem> items = reader.getItems();

        // Verify
        assertEquals(3, items.size());
    }

    @Test
    public void testValidInternalMultipleMixedItems() throws Exception {
        // Setup
        RssReader reader = new RssReader("src/main/java/com/rssreader/test/resource/validRssInternalItems.xml");
        List<RssItem> items = reader.getItems();

        // Verify
        assertEquals(2, items.size());

        RssItem item = items.get(0);
        assertEquals("First Pokémon GO patch released for iOS: Resolves Google account concerns", item.getTitle());
        assertEquals("The first update for Pokémon GO is now available, but only for iOS.", item.getDescription());
        assertEquals(null, item.getImageUrl());
        assertEquals("http://bulbanews.bulbagarden.net/wiki/First_Pok%C3%A9mon_GO_patch_released_for_iOS", item.getLink());
        assertEquals("13 Jul 2016", item.getPubDate());

        item = items.get(1);
        assertEquals("#Pokemon20: Trainer Andy Mientus", item.getTitle());
        assertEquals("test", item.getDescription());
        assertEquals("https://i2.ytimg.com/vi/qdBHGJk4ZN8/hqdefault.jpg", item.getImageUrl());
        assertEquals("http://www.youtube.com/watch?v=qdBHGJk4ZN8", item.getLink());
        assertEquals("2016-07-11T21:46:22+00:00", item.getPubDate());
    }

    @Test (expected = SAXParseException.class)
    public void testInvalidItems() throws Exception {
        new RssReader("src/main/java/com/rssreader/test/resource/invalidRssItem.xml").getItems();
    }
}