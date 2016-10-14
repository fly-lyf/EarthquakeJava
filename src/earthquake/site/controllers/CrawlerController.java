package earthquake.site.controllers;

import earthquake.site.forms.CrawlerForm;
import earthquake.site.forms.Status;
import earthquake.site.entities.EarthquakeUrls;
import earthquake.site.repositories.UrlsRepository;
import earthquake.site.services.CrawlerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by fly on 2016/10/13.
 */

@Controller
@RequestMapping(value = "/crawler")
public class CrawlerController {

    @Inject
    private UrlsRepository urlsRepository;
    @Inject
    private CrawlerService crawlerService;

    @ResponseBody
    @RequestMapping(value = "")
    public CrawlerForm information() {
        String[] setting = crawlerService.readSetting();
        CrawlerForm crawlerForm = new CrawlerForm();
        crawlerForm.urlsList = (List<EarthquakeUrls>) urlsRepository.getAll();
        if(!Pattern.compile("\\d{14}").matcher(setting[0]).find()){
            crawlerForm.keywords = setting[1];
        }
        crawlerForm.time = setting[0];
        return crawlerForm;
    }

    @ResponseBody
    @RequestMapping(value = "/start")
    public HashMap<String, Integer> startCrawler(String urls, String keywords, String allwords, String denywords) {
        int status = crawlerService.updateParams(urls, keywords, allwords, denywords);
        HashMap<String, Integer> statusMap = new HashMap<>();
        if (status == Status.SUCCESS.getValue()) {
            status = crawlerService.startCrawler();
            statusMap.put("status", status);
            return statusMap;

        } else {
            statusMap.put("status", Status.FAIL.getValue());
            return statusMap;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/stop")
    public HashMap<String, Integer> stopCrawler() {
        int status = crawlerService.killCrawler();
        HashMap<String, Integer> statusMap = new HashMap<>();
        statusMap.put("status", status);
        return statusMap;
    }
}
