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
import earthquake.site.service.WordCreateService;
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
    @Inject
    private WordCreateService wordCreateService;

    @ResponseBody
    @RequestMapping(value = "/first")
    public HashMap<String, Object> briefFirst(BriefSearchForm briefSearchForm) throws IOException {
        HashMap<String, Object> result = new HashMap<>();
        // 检索获得所有地震列表
        List<EarthquakeInfo> earthquakeInfoList = infoRepository.getEarthquakeInfoByCondition(briefSearchForm);
        result.put("earthquakeInfo", earthquakeInfoList);
        // 若只有一个结果直接显示详细信息，否则显示列表
        if(earthquakeInfoList.size() == 1){
            EarthquakeInfo info = earthquakeInfoList.get(0);
            String eventId = info.getEventId();
            briefSearchForm.setCounty(info.getCounty());
            briefSearchForm.setCity(info.getCity());
            briefSearchForm.setProvince(info.getProvince());
            // 获得行政区域信息
//            List<EarthquakeAdministrativeDivision> division = divisionRepository.getAdministrativeDivisionByCondition(briefSearchForm);
//            result.put("division", division);

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
            // 历史地震记录
            result.put("historyEarthquakeCity", historyEarthquakeCity);

            String province = info.getProvince();
            String city = info.getCity();
            String county = info.getCounty();
            String place ="";
            if(county!=null&&!county.equals("")){
                place = county;
            }else if(city!=null&&!city.equals("")){
                place = city;
            }else {
                place = province;
            }
            // 这一步只是插入数据到数据库中，需要重新获取，数据格式待解析
           EarthquakeAdministrativeDivision earthquakeAdministrativeDivision = outerDataService.getBaike(place);
            result.put("division",earthquakeAdministrativeDivision);
            // 获取天气信息
            Object weatherInfo = outerDataService.getWeather(city);
            result.put("weatherInfo", weatherInfo);
            // 获取周边区县
            Object nearCounty = outerDataService.getNearDistrict(city, county);
            result.put("nearCounty", nearCounty);
            // 获取周边城市
            Object nearCity = outerDataService.getNearDistrict(province, city);
            result.put("nearCity", nearCity);
            // 生成文档
            System.out.println("word create start........");
            wordCreateService.createBasicInfo(
                    earthquakeInfoList.get(0),
                    earthquakeAdministrativeDivision
            );

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
        List<EarthquakeInfo> earthquakeInfos = infoRepository.getByEventId(id);
        EarthquakeInfo earthquakeInfo = earthquakeInfos.get(0);
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
