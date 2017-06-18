package earthquake.site.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;

import earthquake.site.dao.InfoRepository;
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
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fly on 2017/6/17.
 * 各外部数据源获取类
 */

@Service
public class OuterDataService {

    @Inject
    private InfoRepository infoRepository;
    private int bigNumber = 99999;
    private static final Logger log = LogManager.getLogger();
    private static String[] provinces = new String[]{"北京市", "广东省", "山东省", "江苏省", "河南省", "上海市", "河北省", "浙江省", "香港特别行政区", "陕西省", "湖南省", "重庆市", "福建省", "天津市", "云南省", "四川省", "广西壮族自治区", "安徽省", "海南省", "江西省", "湖北省", "山西省", "辽宁省", "台湾省", "黑龙江", "内蒙古自治区", "澳门特别行政区", "贵州省", "甘肃省", "青海省", "新疆维吾尔自治区", "西藏区", "吉林省", "宁夏回族自治区", "北京", "广东", "山东", "江苏", "河南", "上海", "河北", "浙江", "香港", "陕西", "湖南", "重庆", "福建", "天津", "云南", "四川", "广西", "安徽", "海南", "江西", "湖北", "山西", "辽宁", "台湾", "黑龙江", "内蒙古", "澳门", "贵州", "甘肃", "青海", "新疆", "西藏", "吉林", "宁夏"};
    private static String[] division = new String[]{"州", "地区", "市", "盟", "县", "区", "旗", "城区", "行政委员会"};

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

    //地震台网信息全文查询，一个星期执行一次全文查询
    @Scheduled(fixedDelay = 302400_000L, initialDelay = 10_000L)
    @Transactional
    public void getAllSeismicNetwork() throws IOException, InterruptedException {
        int total = 0;
        String request = "http://www.ceic.ac.cn/ajax/search?&&jingdu1=&&jingdu2=&&weidu1=&&weidu2=&&height1=&&height2=&&zhenji1=&&zhenji2=&&callback=jQuery180007914527465449717_1497694137301&_=1497694241407&&page=";
        String resText = getEntity(request);
        resText = resText.substring(resText.indexOf("(") + 1, resText.lastIndexOf(")"));
        JSONObject resJson;
        try {
            resJson = JSON.parseObject(resText);
            total = (int) resJson.get("num");
        }catch (Exception e){
            log.error(e);
            for (int i = 0; i < e.getStackTrace().length; i++) {
                log.error(e.getStackTrace()[i]);
            }
        }
        for (int i = 1; i <= total; i++) {
            resText = getEntity(request+i);
            EarthquakeInfoParser(resText);
            Thread.sleep(3000);
        }
    }

    //地震台网信息轮询
    @Scheduled(fixedDelay = 43200_000L, initialDelay = 1800_000L)
    @Transactional
    public void getSeismicNetwork() throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String startDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        String request = "http://www.ceic.ac.cn/ajax/search?page=1&&start=" + startDate + "&&end=" + endDate + "&&jingdu1=&&jingdu2=&&weidu1=&&weidu2=&&height1=&&height2=&&zhenji1=&&zhenji2=&&callback=jQuery180007914527465449717_1497694137301&_=1497694241407";

        String resText = getEntity(request);
        EarthquakeInfoParser(resText);
    }

    //解析请求并保存到数据库中，如果地震发生地解析失败，会将发生地信息存入undealed字段留待人工解析
    public void EarthquakeInfoParser(String resText) {
        resText = resText.substring(resText.indexOf("(") + 1, resText.lastIndexOf(")"));
        JSONObject resJson;
        JSONArray earthquakeJSONs = null;
        try {
            resJson = JSON.parseObject(resText);
            earthquakeJSONs = (JSONArray) resJson.get("shuju");
        } catch (Exception e) {
            log.error(e);
            for (int i = 0; i < e.getStackTrace().length; i++) {
                log.error(e.getStackTrace()[i]);
            }
        }
        List<EarthquakeInfo> infoList = new ArrayList<>();
        for (Object earthquakeObj : earthquakeJSONs) {
            EarthquakeInfo info = new EarthquakeInfo();
            JSONObject json = (JSONObject) earthquakeObj;

            String timeStr = (String) json.get("O_TIME");
            Timestamp timestamp = Timestamp.valueOf(timeStr);
            info.setEarthquakeTime(timestamp);

            String eventId = timeStr.replaceAll("-", "");
            eventId = eventId.replaceAll(" ", "");
            eventId = eventId.replaceAll(":", "");
            eventId = eventId.substring(0, eventId.length() - 2) + "00";
            info.setEventId(eventId);

            info.setMagnitude(Double.valueOf((String) json.get("M")));
            info.setDepth(Double.valueOf((Integer) json.get("EPI_DEPTH")));
            info.setLatitude(Double.valueOf((String) json.get("EPI_LAT")));
            info.setLongitude(Double.valueOf((String) json.get("EPI_LON")));

            String location = (String) json.get("LOCATION_C");
            int provincePosition = -1, cityPosition = -1, countyPosition = -1;

            //台湾宜兰县，北京门头沟区，没有市级行政区的处理
            if (location.contains("台湾") || location.contains("北京") || location.contains("天津") || location.contains("重庆") || location.contains("上海")) {
                for (String s : provinces) {
                    if (location.contains(s)) {
                        provincePosition = s.length();
                        cityPosition = provincePosition;
                        break;
                    }
                }
                countyPosition = Math.max(location.indexOf("县"), location.indexOf("市"));
                countyPosition = Math.max(countyPosition, location.indexOf("区"));
                countyPosition++;
            } else {
                //正常的处理
                String locationStr = location;
                ArrayList<Integer> divPosition = new ArrayList<>();

                for (String s : provinces) {
                    if (location.contains(s)) {
                        provincePosition = s.length();
                        break;
                    }
                }
                //不是中国的就跳过
                if (provincePosition == -1) {
                    continue;
                }

                locationStr = locationStr.substring(provincePosition, locationStr.length());
                while (locationStr.length() > 0) {
                    HashMap<String, Integer> divPositionMap = new HashMap<>();
                    for (int i = 0; i < division.length; i++) {
                        String div = division[i];
                        if (locationStr.contains(div)) {
                            //内层if处理郴州市，原州区，沙市区
                            if (!div.equals("州") || locationStr.indexOf("市") - locationStr.indexOf("州") != 1) {
                                divPositionMap.put(div, locationStr.indexOf(div) + div.length());
                            }
                        }
                    }
                    int position = bigNumber;
                    for (Map.Entry<String, Integer> stringIntegerEntry : divPositionMap.entrySet()) {
                        if (stringIntegerEntry.getValue() < position) {
                            position = stringIntegerEntry.getValue();
                        }
                    }
                    if (position == bigNumber) {
                        break;
                    }
                    divPosition.add(position);
                    locationStr = locationStr.substring(position, locationStr.length());
                }
                if (divPosition.size() != 2) {
                    log.warn("发生地解析失败:" + location + " " + provincePosition + " " + cityPosition + " " + countyPosition);
                    System.out.println("发生地解析失败:" + location + " " + provincePosition + " " + cityPosition + " " + countyPosition);
                    cityPosition = 0;
                    countyPosition = 0;
                } else {
                    cityPosition = provincePosition + divPosition.get(0);
                    countyPosition = cityPosition + divPosition.get(1);
                }
            }

            String province, city, county;

            if (cityPosition != 0 && countyPosition != 0) {
                province = location.substring(0, provincePosition);
                city = location.substring(provincePosition, cityPosition);
                county = location.substring(cityPosition, countyPosition);
                info.setProvince(province);
                info.setCity(city);
                info.setCounty(county);
            } else {
                info.setUndealed(location);
            }
            if (infoRepository.getByEventId(eventId).size() == 0) {
                infoList.add(info);
            }
        }
        infoRepository.batchInsert(infoList);
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

}