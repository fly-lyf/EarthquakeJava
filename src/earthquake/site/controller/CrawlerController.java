package earthquake.site.controller;

import earthquake.site.services.CrawlerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;

/**
 * Created by fly on 2016/10/13.
 */

@Controller
@RequestMapping(value = "/crawler")
public class CrawlerController {

    @Inject
    private CrawlerService crawlerService;

    @ResponseBody
    @RequestMapping(value = "/start")
    public HashMap<String, String> startCrawler() {
        String status = crawlerService.startCrawler();
        HashMap<String, String> statusMap = new HashMap<>();
        statusMap.put("status", status);
        return statusMap;
    }

    @ResponseBody
    @RequestMapping(value = "/stop")
    public HashMap<String, String> stopCrawler() {
        String status = crawlerService.killCrawler();
        HashMap<String, String> statusMap = new HashMap<>();
        statusMap.put("status", status);
        return statusMap;
    }

    @ResponseBody
    @RequestMapping(value = "/updateKeywords")
    public HashMap<String, String> updateKeywords() {
        String status = crawlerService.setKeywords("12","12","12");
        HashMap<String, String> statusMap = new HashMap<>();
        statusMap.put("status", status);
        return statusMap;
    }
}
