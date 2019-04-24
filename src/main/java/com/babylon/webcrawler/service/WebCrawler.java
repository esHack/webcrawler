package com.babylon.webcrawler.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Class responsible for processing the url and setting the values in map
 */
@Service
public class WebCrawler {
    private static Map<String, Boolean> map = new HashMap();;
    private static JSoupParser jSoupParser = new JSoupParser();

    public static int getSize() {
        return map.size();
    }

    /**
     *
     * Method to process url based on depth
     * @param URL url
     * @param depth depth
     * @return map of url crawled
     */
    public static Map<String, Boolean> processURL(String URL, int depth) {
        map.clear();
        jSoupParser.getPageLinks(URL,map);
        processBasedOnDepth(depth);
        return map;
    }

    /**
     *
     * Depth is parameter which controls how deep we want to crawl
     * Add true to url which are already visited. URLs that not visited
     * will be visited in next iteration
     * @param depth depth
     */
    private static void processBasedOnDepth(int depth) {

        for (int i = 0; i < depth-1; i++) {
            Map<String, Boolean> temp = new HashMap(map);

            for (Map.Entry<String, Boolean> entry : temp.entrySet()) {
                if (!entry.getValue()) {
                    jSoupParser.getPageLinks(entry.getKey(),map);
                    map.put(entry.getKey(), true);
                }
            }
        }
    }
}