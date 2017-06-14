package earthquake.site.controllers;

import earthquake.site.entity.EarthquakeWebpages;
import earthquake.site.dao.WebpagesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by fly on 2016/10/13.
 */

@Controller
public class DetailController {

    @Inject
    private WebpagesRepository webpagesRepository;

    @ResponseBody
    @RequestMapping(value = "/contentdetail", params="id")
    public EarthquakeWebpages information(String id) {
        int idInt = Integer.parseInt(id);
        EarthquakeWebpages webpage = webpagesRepository.getById(idInt);
        return webpage;
    }
}
