package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRelation;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
public interface EarthQuakeHistoryDAO {

    /*
    * 增加地震的历史信息
    * 实际上 就是关系数据增加到earth_relation表中
    * int earth_id  int earth_history
    * */
    public void addEarthQuakeHistory(int earth_id, int earth_history);

    /*
    * 查询某地震的历史信息id
    * int earth_id
    * */
    public List<EarthquakeRelation> getEarthRelationByEarthId(int earth_id);


}
