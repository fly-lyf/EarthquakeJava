package earthquake.site.controllers;

import earthquake.site.dao.DivisionRepository;
import earthquake.site.dao.InfoRepository;
import earthquake.site.entity.EarthquakeAdministrativeDivision;
import earthquake.site.entity.EarthquakeDeployEntity;
import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.entity.EarthquakeRespond;
import earthquake.site.forms.BriefSearchForm;
import earthquake.site.service.OuterDataService;
import earthquake.site.service.RespondService;
import earthquake.site.service.StatusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fly on 2017/6/13.
 * 简报
 */

@Controller
@RequestMapping(value = "/brief")
public class BriefController {

    @Inject
    private InfoRepository infoRepository;
    @Inject
    private DivisionRepository divisionRepository;
    @Inject
    private OuterDataService outerDataService;
    @Inject
    private StatusService statusService;
    @Inject
    private RespondService respondService;

    @ResponseBody
    @RequestMapping(value = "/first")
    public HashMap<String, Object> briefFirst(BriefSearchForm briefSearchForm) throws IOException {
        HashMap<String, Object> result = new HashMap<>();

        List<EarthquakeInfo> earthquakeInfoList = infoRepository.getEarthquakeInfoByCondition(briefSearchForm);
        result.put("earthquakeInfo", earthquakeInfoList);
        System.out.print("earthquakeInfo"+earthquakeInfoList);
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
            result.put("historyEarthquakeCounty", historyEarthquakeCounty);

            for (EarthquakeInfo earthquakeInfo : historyEarthquake) {
                if (!info.getCounty().contains(earthquakeInfo.getCounty()) && !earthquakeInfo.getCounty().contains(info.getCounty())){
                    historyEarthquakeCity.add(earthquakeInfo);
                }
            }
            result.put("historyEarthquakeCity", historyEarthquakeCity);

            String province = info.getProvince();
            String city = info.getCity();
            String county = info.getCounty();

            Object weatherInfo = outerDataService.getWeather(city);
            result.put("weatherInfo", weatherInfo);

            Object nearCounty = outerDataService.getNearDistrict(city, county);
            result.put("nearCounty", nearCounty);

            Object nearCity = outerDataService.getNearDistrict(province, city);
            result.put("nearCity", nearCity);

        }else{
            result.put("pageTotal", earthquakeInfoList.size());
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/second",params = "id")
    public HashMap<String,Object> briefSecond(String id) throws IOException{
//        HashMap<String, Object> result = new HashMap<>();
//        List<EarthquakeRule> statusInfo = statusRepository.getStatusByCondition(statusSearchForm);
//        result.put("statusInfo", statusInfo);
//        System.out.print("result"+result);
        HashMap<String, Object> result = new HashMap<>();
        // 获取相应等级信息
        List<EarthquakeRespond> responds = statusService.getRespond(id);
        result.put("respond",responds);
        // 获取应急处理方案
        List<EarthquakeDeployEntity> deploys = respondService.getRespondDeploy(id);
        result.put("deploys",deploys);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/third")
    public String briefThird(){
        return null;
    }
}
