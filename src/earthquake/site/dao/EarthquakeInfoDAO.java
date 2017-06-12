package earthquake.site.dao;

import earthquake.site.entity.EarthquakeInfo;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
public interface EarthquakeInfoDAO {

    /*
    *  获取所有 地震信息
    *  int pageNumber 页码
    *  用于分页
    * */
    public List<EarthquakeInfo> getAllEarthQuake(int pageNumber);

    /*
    * 根据时间查询 地震信息
    * String time
    * */
    public List<EarthquakeInfo> getEarthQuakeByTime(String time);

    /*
    * 根据城市关键字查询地震信息
    * String city
    * */
    public List<EarthquakeInfo> getEarthQuakeByCity(String city);

    /*
    * 增加地震信息
    * String earth_title
    * String earth_time
    * double longitude
    * double latitude
    * int deepth
    * double magnitude
    * String city
    * */
    public void addEarthQuakeInfo(String earth_title, String earth_time,
                                  double longitude, double latitude, int deepth, double magnitude, String city);

    /*
    * 删除地震信息
    * int earth_id
    * */
    public void deleteEarthQuake(int earth_id);


    /*
    * 根据earth_id 或者earth_history 来获取地震信息
    * int earth_id
    * */
    public EarthquakeInfo getEarthQuakeByID(int earth_id);

    /*
    * 根据earth_id 或者earth_history 来获取地震信息
    * List<Integer> earth_id
    * */
    public List<EarthquakeInfo> getEarthQuakeByID(List<Integer> earth_id);




}
