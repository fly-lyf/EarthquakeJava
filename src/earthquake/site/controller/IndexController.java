package earthquake.site.controller;

import earthquake.site.SearchForm;
import earthquake.site.entities.EarthquakeLeftbar;
import earthquake.site.entities.EarthquakeWebpages;
import earthquake.site.repositories.interfaces.LeftBarRepository;
import earthquake.site.repositories.interfaces.WebpagesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by earthquake on 2016/10/8.
 */

@Controller
public class IndexController {

    @Inject
    private LeftBarRepository leftBarRepository;
    @Inject
    private WebpagesRepository webpagesRepository;
    private static ArrayList<String> leftBar = new ArrayList<>();

    @ResponseBody
    @RequestMapping(value = "/leftBar")
    public Iterable<String> leftBarList() {
        if(leftBar.size()<=0){
            Iterable<EarthquakeLeftbar> leftBarEntities = leftBarRepository.getAll();
            for (Iterator<EarthquakeLeftbar> iterator = leftBarEntities.iterator(); iterator.hasNext(); ) {
                EarthquakeLeftbar next = iterator.next();
                leftBar.add(next.getColumn());
            }
        }
       return leftBar;
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public Iterable<EarthquakeWebpages> searchList(Map<String, String> conditions) {
       return webpagesRepository.getByCondition(conditions);
    }
}
