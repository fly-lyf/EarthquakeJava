package earthquake.site.service;

import earthquake.site.dao.EarthquakeLossDAO;
import earthquake.site.dao.EarthquakeRespondDAO;
import earthquake.site.dao.EarthquakeRuleRespondDAO;
import earthquake.site.entity.EarthquakeLoss;
import earthquake.site.entity.EarthquakeRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frankstar on 2017/6/10.
 */
@Service
public class EarthquakeRespondServiceImpl implements EarthquakeRespondService {

    @Autowired
    private EarthquakeRespondDAO earthquakeRespondDAO;

    @Autowired
    private EarthquakeRuleRespondDAO earthquakeRuleRespondDAO;

    @Autowired
    private EarthquakeLossDAO earthquakeLossDAO;

    @Override
    public List<EarthquakeRespond> getAllEarthQuakeRespond() {

        List<EarthquakeRespond> earthQuakeRespondList = earthquakeRespondDAO.getAllEarthQuakeRespond();
        return earthQuakeRespondList;
    }

    @Override
    public EarthquakeRespond getEarthQuakeRespondByRuleID(int rule_id) {

        EarthquakeRespond earthQuakeRespond = earthquakeRespondDAO.getEarthQuakeRespondByRespondID(earthquakeRuleRespondDAO.getRespondIDByRuleID(rule_id));

        return earthQuakeRespond;
    }

    @Override
    public EarthquakeRespond getEarthQuakeRespondByEarthID(int earth_id) {

        EarthquakeLoss earthQuakeLoss = earthquakeLossDAO.getEarthQuakeLossByEarthID(earth_id);
        EarthquakeRespond earthQuakeRespond = null;

        if(earthQuakeLoss.getDeathPeople() > 0) {
            //此处根据死亡人数 地震等级规则
            int death_people = earthQuakeLoss.getDeathPeople();

            earthQuakeRespond = getEarthQuakeRespondByDeathPeople(death_people);

        }else if(earthQuakeLoss.getEconomyLoss() > 0) {
            //此处根据经济损失划分地震等级
            double economy_loss = earthQuakeLoss.getEconomyLoss();
            int rule_id = 0;

            earthQuakeRespond = getEarthQuakeRespondByEconomyLoss(economy_loss);

        }


        return earthQuakeRespond;
    }

    @Override
    public EarthquakeRespond getEarthQuakeRespondByMagnitude(double magnitude) {

        EarthquakeRespond earthQuakeRespond;

        int rule_id = 0;
        //进行逻辑判断
        if (magnitude >= 7.0) {
            rule_id = 4;
        }else if(magnitude < 7.0 && magnitude >= 6.5) {
            rule_id = 1;
        }else if(magnitude < 6.5 && magnitude >= 6) {
            rule_id = 2;
        }else if(magnitude < 6.0 && magnitude >= 0) {
            rule_id = 3;
        }

        earthQuakeRespond = earthquakeRespondDAO.getEarthQuakeRespondByRespondID(earthquakeRuleRespondDAO.getRespondIDByRuleID(rule_id));
        return earthQuakeRespond;
    }

    @Override
    public EarthquakeRespond getEarthQuakeRespondByDeathPeople(int death_people) {

        EarthquakeRespond earthQuakeRespond;

        int rule_id = 0;
        //进行逻辑判断
        if(death_people >= 300) {
            rule_id = 4;
        }else if (death_people < 300 && death_people >= 50) {
            rule_id = 1;
        }else if (death_people < 50 && death_people >= 20) {
            rule_id = 2;
        }else if (death_people < 20 && death_people >= 0 ) {
            rule_id = 3;
        }

        earthQuakeRespond = earthquakeRespondDAO.getEarthQuakeRespondByRespondID(earthquakeRuleRespondDAO.getRespondIDByRuleID(rule_id));
        return earthQuakeRespond;
    }

    @Override
    public EarthquakeRespond getEarthQuakeRespondByEconomyLoss(double economy_loss) {

        EarthquakeRespond earthQuakeRespond;
        int rule_id = 0;
        //进行逻辑判断
        if(economy_loss > 0.1) {
            rule_id = 4;
        }

        earthQuakeRespond = earthquakeRespondDAO.getEarthQuakeRespondByRespondID(earthquakeRuleRespondDAO.getRespondIDByRuleID(rule_id));
        return earthQuakeRespond;
    }
}
