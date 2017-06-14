package earthquake.site.controllers;

import earthquake.site.dao.InfoRepository;
import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.forms.BriefSearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by fly on 2017/6/13.
 */

@Controller
@RequestMapping(value = "/brief")
public class BriefController {

    @Inject
    private InfoRepository infoRepository;

    @RequestMapping(value = "/first")
    public  List<EarthquakeInfo> briefFirst(BriefSearchForm briefSearchForm){
        List<EarthquakeInfo> earthquakeInfoList = infoRepository.getEarthquakeInfoByCondition(briefSearchForm);
        return earthquakeInfoList;
    }

    @RequestMapping(value = "/second")
    public String briefSecond(){

        return null;
    }

    @RequestMapping(value = "/third")
    public String briefThird(){

        return null;
    }
}
