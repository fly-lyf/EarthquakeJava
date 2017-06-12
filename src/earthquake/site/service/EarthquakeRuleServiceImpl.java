package earthquake.site.service;

import earthquake.site.dao.EarthquakeLossDAO;
import earthquake.site.dao.EarthquakeRuleDAO;
import earthquake.site.entity.EarthquakeLoss;
import earthquake.site.entity.EarthquakeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frankstar on 2017/6/10.
 */
@Service
public class EarthquakeRuleServiceImpl implements EarthquakeRuleService {

    @Autowired
    private EarthquakeRuleDAO earthquakeRuleDAO;

    @Autowired
    private EarthquakeLossDAO earthquakeLossDAO;

    @Override
    public List<EarthquakeRule> getAllEarthQuakeRule() {
        return earthquakeRuleDAO.getAllEarthQuakeRule();
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByRuleID(int rule_id) {
        return earthquakeRuleDAO.getEarthQuakeRuleByRuleID(rule_id);
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByEarthMagnitude(double magnitude) {

        EarthquakeRule earthquakeRule;
        int rule_id = 0;
        //½øÐÐÂß¼­ÅÐ¶Ï
        if (magnitude >= 7.0) {
            rule_id = 4;
        }else if(magnitude < 7.0 && magnitude >= 6.5) {
            rule_id = 1;
        }else if(magnitude < 6.5 && magnitude >= 6) {
            rule_id = 2;
        }else if(magnitude < 6.0 && magnitude >= 0) {
            rule_id = 3;
        }
        earthquakeRule = getEarthQuakeRuleByRuleID(rule_id);
        return earthquakeRule;
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByDeathPeople(int death_people) {

        EarthquakeRule earthquakeRule;
        int rule_id = 0;
        //½øÐÐÂß¼­ÅÐ¶Ï
        if(death_people >= 300) {
            rule_id = 4;
        }else if (death_people < 300 && death_people >= 50) {
            rule_id = 1;
        }else if (death_people < 50 && death_people >= 20) {
            rule_id = 2;
        }else if (death_people < 20 && death_people >= 0 ) {
            rule_id = 3;
        }
        earthquakeRule = getEarthQuakeRuleByRuleID(rule_id);
        return earthquakeRule;
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByEconomyLoss(double economy_loss) {

        EarthquakeRule earthquakeRule;
        int rule_id = 0;
        //½øÐÐÂß¼­ÅÐ¶Ï
        if(economy_loss > 0.1) {
            rule_id = 4;
        }
        earthquakeRule = getEarthQuakeRuleByRuleID(rule_id);
        return earthquakeRule;
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByEarthID(int earth_id) {

        EarthquakeRule earthquakeRule = null;
        EarthquakeLoss earthQuakeLoss = earthquakeLossDAO.getEarthQuakeLossByEarthID(earth_id);

        if(earthQuakeLoss.getDeathPeople() > 0) {
            int death_people = earthQuakeLoss.getDeathPeople();
            earthquakeRule = getEarthQuakeRuleByDeathPeople(death_people);
        }else if(earthQuakeLoss.getEconomyLoss() > 0) {
            double economy_loss = earthQuakeLoss.getEconomyLoss();
            earthquakeRule = getEarthQuakeRuleByEconomyLoss(economy_loss);
        }

        return earthquakeRule;
    }
}
