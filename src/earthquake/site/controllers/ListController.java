package earthquake.site.controllers;

import earthquake.site.forms.WebpageSearchForm;
import earthquake.site.entity.EarthquakeLeftbar;
import earthquake.site.dao.LeftBarRepository;
import earthquake.site.dao.WebpagesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by earthquake on 2016/10/8.
 * 展示页面控制器
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
    public HashMap<String, Object> searchList(WebpageSearchForm form) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("list", webpagesRepository.getWebpagesByCondition(form));
        result.put("pageTotal", webpagesRepository.getCount(form));
        return result;
    }
}
