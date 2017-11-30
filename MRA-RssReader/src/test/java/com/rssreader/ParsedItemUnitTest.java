//package com.rssreader;
//
//
//public class ParsedItemUnitTest {
//
//    @Test
//    public void testSetGetTitle() throws Exception {
//        // Setup
//        ParsedItem item = new ParsedItem();
//        item.setTitle("<div><p>A Wild pokemon is here!</p></div>");
//
//        //Verify
//        assertEquals(item.getTitle(), "A Wild pokemon is here!");
//    }
//
//    @Test
//    public void testSetGetDescription() throws Exception {
//        // Setup
//        ParsedItem item = new ParsedItem();
//        item.setDescription("<div><p>A Wild pokemon is here!</p></div>");
//
//        //Verify
//        assertEquals(item.getDescription(), "A Wild pokemon is here!");
//    }
//
//    @Test
//    public void testSetGetPubDateWrong() throws Exception {
//        // Setup
//        ParsedItem item = new ParsedItem();
//        item.setPubDate("2016-09-09 22:14:09");
//
//        //Verify
//        assertEquals(item.getPubDate(), "2016-09-09 22:14:09");
//    }
//
//    @Test
//    public void testSetGetPubDateRight() throws Exception {
//        // Setup
//        ParsedItem item = new ParsedItem();
//        item.setPubDate("Wed, 09 April 1999 07:07:07 -0800");
//
//        //Verify
//        assertEquals(item.getPubDate(), "09 Apr 1999");
//    }
//
//    @Test
//    public void testSetGetLink() throws Exception {
//        // Setup
//        ParsedItem item = new ParsedItem();
//        item.setLink("https://www.pokemon.nl");
//
//        //Verify
//        assertEquals(item.getLink(), "https://www.pokemon.nl");
//    }
//
//    @Test
//    public void testSetGetImageUrl() throws Exception {
//        // Setup
//        ParsedItem item = new ParsedItem();
//        item.setImageUrl("www.google.nl/image.png");
//
//        //Verify
//        assertEquals(item.getImageUrl(), "www.google.nl/image.png");
//    }
//}