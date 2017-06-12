package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRespond;

import java.util.List;

/**
 * Created by frankstar on 2017/6/9.
 */
public interface EarthquakeRespondDAO {

    /*
    * 列出所有的响应方案
    * */
    public List<EarthquakeRespond> getAllEarthQuakeRespond();

    /*
    * 根据地震响应ID来确定响应方案
    * int respond_id
    * */
    public EarthquakeRespond getEarthQuakeRespondByRespondID(int respond_id);

    /*
    * 根据地震ID来确定响应方案
    * int earth_id
    * 这个暂定可以放在服务层
    * */
    //public EarthquakeRespond getEarthQuakeRespondByEarthID(int earth_id);
}
