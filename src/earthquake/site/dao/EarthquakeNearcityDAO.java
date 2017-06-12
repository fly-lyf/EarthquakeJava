package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRelation;
import earthquake.site.entity.EarthquakeNearcityInfo;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
public interface EarthquakeNearcityDAO {

    /*
    * 增加地震的附近信息
    * 实际上 就是关系数据增加到earth_relation表中
    * int earth_id  int earth_near
    * */
    public void addEarthQuakeNearCity(int earth_id, int earth_near);

    /*
    * 查询某地震的附近信息id
    * int earth_id
    * */
    public List<EarthquakeRelation> getEarthRelationByEarthID(int earth_id);

    /*
    * 根据earth_near查询附近信息
    * List<Integer> earth_near
    * */

    public List<EarthquakeNearcityInfo> getNearCityInfoByEarthNearID(List<Integer> earthNear);

    /*
    * 根据earth_near查询附近信息
    * int earth_near
    * */

    public EarthquakeNearcityInfo getNearCityInfoByEarthNearID(int earthNear);
}
