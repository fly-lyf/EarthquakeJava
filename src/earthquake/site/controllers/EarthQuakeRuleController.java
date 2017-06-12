package earthquake.site.controllers;

import earthquake.site.entity.EarthquakeRule;
import earthquake.site.service.EarthquakeRuleService;
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
@RequestMapping(value = "/rule")
public class EarthQuakeRuleController {

    @Autowired
    private EarthquakeRuleService earthquakeRuleService;

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public String getAllEarthRule(HttpServletRequest httpServletRequest) {

        List<EarthquakeRule> earthQuakeRuleList = earthquakeRuleService.getAllEarthQuakeRule();
        httpServletRequest.setAttribute("earthQuakeRuleList", earthQuakeRuleList);

        return "allEarthRule";

    }

    @RequestMapping(value="/earthID",method = RequestMethod.GET)
    public String getEarthRuleByEarthID(@RequestParam("earth_id") int earth_id, HttpServletRequest httpServletRequest) {

        EarthquakeRule earthquakeRule = earthquakeRuleService.getEarthQuakeRuleByEarthID(earth_id);
        httpServletRequest.setAttribute("earthquakeRule", earthquakeRule);

        return "earthRule";

    }




}
