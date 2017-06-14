package earthquake.site.controllers;

import com.alibaba.fastjson.JSON;
import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.entity.EarthquakeNearcityInfo;
import earthquake.site.service.EarthquakeHistoryInfoService;
import earthquake.site.service.EarthquakeInfoService;
import earthquake.site.service.EarthquakeNearcityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by frankstar on 2017/6/7.
 */
@Controller
@RequestMapping(value = "/info")
public class EarthQuakeController {

    @Autowired
    private EarthquakeInfoService earthquakeInfoService;

    @Autowired
    private EarthquakeNearcityInfoService earthquakeNearcityInfoService;

    @Autowired
    private EarthquakeHistoryInfoService earthquakeHistoryInfoService;

    /*@RequestMapping(value="/local",method = RequestMethod.GET)
    public String getAllEarthQuakeInfo(@RequestParam("pageNumber") int pageNumber, HttpServletRequest httpServletRequest) {

        List<EarthquakeInfo> earthQuakeList = earthQuakeInfoService.getAllEarthQuakeInfo(pageNumber);
        httpServletRequest.setAttribute("earthQuakeList", earthQuakeList);

        return "getAll";
    }*/

    @RequestMapping(value="/local",method = RequestMethod.GET)
    @ResponseBody
    public String getAllEarthQuakeInfo(@RequestParam("pageNumber") int pageNumber) {

        List<EarthquakeInfo> earthQuakeList = earthquakeInfoService.getAllEarthQuakeInfo(pageNumber);
        //将List对象转换为JSON对象
        String earthquakeInfo = JSON.toJSONString(earthQuakeList);

        return earthquakeInfo;
    }

    @RequestMapping(value="/history",method = RequestMethod.GET)
    public String getEarthQuakeHistoryInfo(@RequestParam("earth_id") int earth_id, HttpServletRequest httpServletRequest) {
        //httpServletRequest.setAttribute("earthQuakeList", earthQuakeList);

        List<EarthquakeInfo> earthQuakeList = earthquakeHistoryInfoService.getEarthQuakeHistoryInfo(earth_id);
        httpServletRequest.setAttribute("earthQuakeHistoryInfo", earthQuakeList);

        return "getHistory";
    }

    @RequestMapping(value="/near",method = RequestMethod.GET)
    public String getEarthQuakeNearCityInfo(@RequestParam("earth_id") int earth_id, HttpServletRequest httpServletRequest) {

        List<EarthquakeNearcityInfo> nearCityList = earthquakeNearcityInfoService.getEarthQuakeNearCity(earth_id);
        httpServletRequest.setAttribute("earthQuakeNearCityInfo", nearCityList);

        return "getNearCity";
    }


}
