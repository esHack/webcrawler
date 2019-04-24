package com.babylon.webcrawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 *
 * Class responsible for fetching urls in a website
 *
 */
public class JSoupParser {

    Logger logger = LoggerFactory.getLogger(JSoupParser.class);

    /**
     * Method to get the connection to url and fetch all the urls on the website
     * @param URL url to processed
     * @param map map of urls
     */
    public void getPageLinks(String URL, Map<String, Boolean> map) {
        try {
            Document document = Jsoup.connect(URL).get();
            Elements otherLinks = document.select("a[href]");

            for (Element page : otherLinks) {

                if (page.attr("abs:href").contains(URL)) {
                    System.out.println(page.attr("abs:href"));
                    map.putIfAbsent(page.attr("abs:href"), false);
                }

            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            map.put(URL, true);
        }
    }
}
