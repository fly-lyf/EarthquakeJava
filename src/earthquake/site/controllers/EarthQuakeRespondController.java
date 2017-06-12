package earthquake.site.controllers;

import earthquake.site.entity.EarthquakeRespond;
import earthquake.site.service.EarthquakeRespondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by frankstar on 2017/6/10.
 */
@Controller
@RequestMapping(value = "/respond")
public class EarthQuakeRespondController {

    @Autowired
    private EarthquakeRespondService earthquakeRespondService;

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public String getAllEarthRespond(HttpServletRequest httpServletRequest) {

        List<EarthquakeRespond> earthQuakeRespondList = earthquakeRespondService.getAllEarthQuakeRespond();
        httpServletRequest.setAttribute("earthQuakeRespondList", earthQuakeRespondList);

        return "allEarthRespond";

    }

    @RequestMapping(value="/earthID",method = RequestMethod.GET)
    public String getEarthRuleByEarthID(@RequestParam("earth_id") int earth_id, HttpServletRequest httpServletRequest) {

        EarthquakeRespond earthQuakeRespond = earthquakeRespondService.getEarthQuakeRespondByEarthID(earth_id);
        httpServletRequest.setAttribute("earthQuakeRespond", earthQuakeRespond);

        return "earthRespond";

    }
}
