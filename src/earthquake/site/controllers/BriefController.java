package earthquake.site.controllers;

import earthquake.site.dao.DivisionRepository;
import earthquake.site.dao.InfoRepository;
import earthquake.site.entity.EarthquakeAdministrativeDivision;
import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.forms.BriefSearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fly on 2017/6/13.
 */

@Controller
@RequestMapping(value = "/brief")
public class BriefController {

    @Inject
    private InfoRepository infoRepository;
    @Inject
    private DivisionRepository divisionRepository;

    @ResponseBody
    @RequestMapping(value = "/first")
    public HashMap<String, Object> briefFirst(BriefSearchForm briefSearchForm){
        HashMap<String, Object> result = new HashMap<>();

        List<EarthquakeInfo> earthquakeInfoList = infoRepository.getEarthquakeInfoByCondition(briefSearchForm);
        result.put("earthquakeInfo", earthquakeInfoList);

        if(earthquakeInfoList.size() == 1){
            EarthquakeInfo info = earthquakeInfoList.get(0);
            String eventId = info.getEventId();
            briefSearchForm.setCounty(info.getCounty());
            briefSearchForm.setCity(info.getCity());
            briefSearchForm.setProvince(info.getProvince());
            List<EarthquakeAdministrativeDivision> division = divisionRepository.getAdministrativeDivisionByCondition(briefSearchForm);
            result.put("division", division);

            BriefSearchForm historyForm = new BriefSearchForm();

            historyForm.setCity(info.getCity());
            historyForm.setProvince(info.getProvince());
            List<EarthquakeInfo> historyEarthquake = infoRepository.getEarthquakeInfoByCondition(historyForm);

            List<EarthquakeInfo> historyEarthquakeCounty = new ArrayList<>();
            List<EarthquakeInfo> historyEarthquakeCity = new ArrayList<>();

            for (EarthquakeInfo earthquakeInfo : historyEarthquake) {
                if(!earthquakeInfo.getEventId().equals(eventId) && (info.getCounty().contains(earthquakeInfo.getCounty()) || earthquakeInfo.getCounty().contains(info.getCounty()))){
                    historyEarthquakeCounty.add(earthquakeInfo);
                }
            }

            for (EarthquakeInfo earthquakeInfo : historyEarthquake) {
                if (!info.getCounty().contains(earthquakeInfo.getCounty()) && !earthquakeInfo.getCounty().contains(info.getCounty())){
                    historyEarthquakeCity.add(earthquakeInfo);
                }
            }

            result.put("historyEarthquakeCity", historyEarthquakeCity);

            result.put("historyEarthquakeCounty", historyEarthquakeCounty);

        }else{
            result.put("pageTotal", earthquakeInfoList.size());
        }
        return result;
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
