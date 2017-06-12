package earthquake.site.dao;

import earthquake.site.entity.EarthquakeLoss;

/**
 * Created by frankstar on 2017/6/9.
 */
public interface EarthquakeLossDAO {

    /*
    * 根据地震ID 列出地震造成的损失
    * int earth_id
    * */
    public EarthquakeLoss getEarthQuakeLossByEarthID(int earth_id);

    /*
    * 添加某地震的损失信息
    * int earth_id
    * int death_people
    * int injury
    * double economy_loss
    * double destroy
    * */
    public void addEarthQuakeLoss(int earth_id, int death_people, int injury, double economy_loss, double destroy);
}
