package earthquake.site.controllers;

import earthquake.site.forms.SearchForm;
import earthquake.site.entities.EarthquakeLeftbar;
import earthquake.site.repositories.LeftBarRepository;
import earthquake.site.repositories.WebpagesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
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

    private static ArrayList<EarthquakeLeftbar> leftBar = new ArrayList<EarthquakeLeftbar>();

    @ResponseBody
    @RequestMapping(value = "/leftbar")
    public ArrayList<EarthquakeLeftbar> leftBarList() {
        if (leftBar.size() <= 0) {
            leftBar = (ArrayList<EarthquakeLeftbar>) leftBarRepository.getAll();
        }
        return leftBar;
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public HashMap<String, Object> searchList(SearchForm form) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("list", webpagesRepository.getByCondition(form));
        result.put("pageTotal", webpagesRepository.getCount(form));
        return result;
    }
}
