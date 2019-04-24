package com.babylon.webcrawler.controller;

import com.babylon.webcrawler.service.WebCrawler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 * Rest Controller to display urls processed
 */
@Controller
public class RestController {

    @GetMapping("/urlcount")
    @ResponseBody
    public int getCount() {
        return WebCrawler.getSize();
    }

    @GetMapping("/processurls")
    @ResponseBody
    public Map<String, Boolean> processURL(@RequestParam(name = "url", required = true,
            defaultValue = "https://www.babylonhealth.com") String url, @RequestParam(name = "depth",
            required = true,
            defaultValue = "3") int depth) {
        System.out.println(url);
        System.out.println(depth);
        Map<String, Boolean> map = WebCrawler.processURL(url, depth);
        return map;
    }

}
