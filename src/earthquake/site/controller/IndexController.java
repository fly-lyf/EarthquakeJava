package earthquake.site.controller;

import earthquake.site.entities.EarthquakeLeftbar;
import earthquake.site.entities.EarthquakeWebpages;
import earthquake.site.repositories.interfaces.LeftBarRepository;
import earthquake.site.repositories.interfaces.WebpagesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
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
    @RequestMapping(value = "/leftbar" , params = {"content"})
    public Iterable<String> leftBarList(@PathVariable String content) {
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
    public Iterable<EarthquakeWebpages> searchList(Map<String, Object> model) {
        System.out.println(model.size());
        return null;
//       return webpagesRepository.getByCondition(conditions);
    }
}
