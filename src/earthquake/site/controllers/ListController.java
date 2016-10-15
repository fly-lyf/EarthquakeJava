package earthquake.site.controllers;

import earthquake.site.forms.SearchForm;
import earthquake.site.entities.EarthquakeLeftbar;
import earthquake.site.entities.EarthquakeWebpages;
import earthquake.site.repositories.LeftBarRepository;
import earthquake.site.repositories.WebpagesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by earthquake on 2016/10/8.
 */

@Controller
public class ListController {

    @Inject
    private LeftBarRepository leftBarRepository;
    @Inject
    private WebpagesRepository webpagesRepository;

    private static ArrayList<String> leftBar = new ArrayList<>();

    @ResponseBody
    @RequestMapping(value = "/leftbar")
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
    public Iterable<EarthquakeWebpages> searchList(SearchForm form) {
        System.out.println(form.getClass());
       return webpagesRepository.getByCondition(form);
    }
}
