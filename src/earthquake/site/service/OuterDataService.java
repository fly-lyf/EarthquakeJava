package earthquake.site.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;

import earthquake.site.entity.EarthquakeInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fly on 2017/6/17.
 * 各外部数据源获取类
 */

@Service
public class OuterDataService {

    private static final Logger log = LogManager.getLogger();

    //天气api，查询成功返回JSONObject格式的所在地区天气信息，失败返回失败字符串,无日访问量限制
    public Object getWeather(String location) throws IOException {
        String request = "http://restapi.amap.com/v3/weather/weatherInfo?key=90a53e1484d6819a7a9e52969c6ceb29&extensions=all&city=" + URLEncoder.encode(location, "utf-8");
        String resText = getEntity(request);
        try {
            JSONObject resJson = JSON.parseObject(resText);
            JSONObject dailyObj = (JSONObject) ((JSONArray) resJson.get("forecasts")).get(0);
            return dailyObj.get("casts");
        } catch (Exception e) {
            log.error(e);
            for (int i = 0; i < e.getStackTrace().length; i++) {
                log.error(e.getStackTrace()[i]);
            }
        }
        return "parse error";
    }

    //地理位置api,subdistrict为往下查询的行政区域的深度
    public Object getNearDistrict(String upperLocation, String lowerLocation) throws IOException {
        String request = "http://restapi.amap.com/v3/config/district?subdistrict=1&key=90a53e1484d6819a7a9e52969c6ceb29&keywords=" + URLEncoder.encode(upperLocation, "utf-8");
        String resText = getEntity(request);
        try {
            JSONObject resJson = JSON.parseObject(resText);
            JSONObject outerDistrictObj = (JSONObject) ((JSONArray) resJson.get("districts")).get(0);
            JSONArray districtObj = (JSONArray) outerDistrictObj.get("districts");
            JSONArray resultJSON = new JSONArray();
            for (Object o : districtObj) {
                for (Map.Entry<String, Object> s : ((JSONObject) o).entrySet()) {
                    if (s.getKey().equals("name")) {
                        String name = (String) s.getValue();
                        if (!name.contains(lowerLocation) && !lowerLocation.contains(name)) {
                            resultJSON.add(o);
                        }
                    }
                }
            }
            return resultJSON;
        } catch (Exception e) {
            log.error(e);
            for (int i = 0; i < e.getStackTrace().length; i++) {
                log.error(e.getStackTrace()[i]);
            }
        }
        return "parse error";
    }


    //地震台网信息轮询
//    @Scheduled(fixedDelay = 43200_000L, initialDelay = 10_000L)
//    @Transactional
    public static List<EarthquakeInfo> getSeismicNetwork() throws IOException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String startDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

        String request = "http://www.ceic.ac.cn/ajax/search?page=1&&start=" + startDate + "&&end=" + endDate + "&&jingdu1=&&jingdu2=&&weidu1=&&weidu2=&&height1=&&height2=&&zhenji1=&&zhenji2=&&callback=jQuery180007914527465449717_1497694137301&_=1497694241407";
        String resText = getEntity(request);
        resText = resText.substring(resText.indexOf("(")+1, resText.lastIndexOf(")"));
        System.out.println(resText);

        try {
            JSONObject resJson = JSON.parseObject(resText);
            JSONArray earthquakeInfos = (JSONArray) resJson.get("shuju");

            return null;
        } catch (Exception e) {
            log.error(e);
            for (int i = 0; i < e.getStackTrace().length; i++) {
                log.error(e.getStackTrace()[i]);
            }
        }
        return null;
    }

    //百度百科信息轮询


    //发送http请求
    public static String getEntity(String url) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.89 Safari/537.36");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String resText = EntityUtils.toString(entity, "utf-8");
        return resText;
    }

    public static void main(String[] args) throws IOException {
        getSeismicNetwork();
    }

}
