package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRuleRespond;

import java.util.List;

/**
 * Created by frankstar on 2017/6/10.
 */
public interface EarthquakeRuleRespondDAO {

    /*
    * 列出所有的对应关系
    * */
    public List<EarthquakeRuleRespond> getAllEarthRuleRespond();

    /*
    *  根据rule_id 查询对应的respond_id
    * */
    public int getRespondIDByRuleID(int rule_id);

    /*
    *    根据respond_id 查询对应的rule_id
    * */
    public int getRuleIDByRespondID(int respond_id);
}
