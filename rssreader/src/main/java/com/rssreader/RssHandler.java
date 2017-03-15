package com.rssreader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class RssHandler extends DefaultHandler {
    private List<ParsedItem> parsedItemList;
    private ParsedItem currentItem;
    private boolean parsingTitle;
    private boolean parsingLink;
    private boolean parsingDescription;
    private boolean parsingDate;

    private String description;
    private String title;
    private String pubDate;
    private String link;

    public RssHandler() {
        parsedItemList = new ArrayList<>();
    }

    public List<ParsedItem> getParsedItemList() {
        return parsedItemList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "entry":
            case "item":
                if (currentItem == null) {
                    currentItem = new ParsedItem();
                    break;
                }
            case "title":
                title = "";
                parsingTitle = true;
                break;
            case "description":
                description = "";
                parsingDescription = true;
                break;
            case "pubDate":
                pubDate = "";
                parsingDate = true;
                break;
            case "media:description":
                description = "";
                parsingDescription = true;
                break;
            case "published":
                pubDate = "";
                parsingDate = true;
                break;
            case "link":
                link = "";
                if (attributes.getValue("href") != null) {
                    link = attributes.getValue("href");
                } else {
                    link = "";
                    parsingLink = true;
                }
                break;
            case "media:thumbnail":
            case "media:content":
            case "img":
                if (attributes.getValue("url") != null)
                    currentItem.setImageUrl(attributes.getValue("url"));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "entry":
            case "item":
                if (currentItem != null) {
                    currentItem.setTitle(title);
                    currentItem.setDescription(description);
                    currentItem.setPubDate(pubDate);
                    currentItem.setLink(link);
                    parsedItemList.add(currentItem);
                    currentItem = null;
                }
                break;
            case "title":
                parsingTitle = false;
                break;
            case "description":
                parsingDescription = false;
                break;
            case "pubDate":
                parsingDate = false;
                break;
            case "media:description":
                parsingDescription = false;
                break;
            case "published":
                parsingDate = false;
                break;
            case "link":
                parsingLink = false;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentItem != null) {
            if (parsingTitle)
                title += new String(ch, start, length);
            else if (parsingDescription)
                description += new String(ch, start, length);
            else if (parsingDate)
                pubDate += new String(ch, start, length);
            else if (parsingLink)
                link += new String(ch, start, length);
        }
    }
}


