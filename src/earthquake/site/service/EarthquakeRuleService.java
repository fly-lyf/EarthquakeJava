package earthquake.site.service;

import earthquake.site.entity.EarthquakeRule;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frankstar on 2017/6/10.
 */
@Service
public interface EarthquakeRuleService {

    public List<EarthquakeRule> getAllEarthQuakeRule();

    public EarthquakeRule getEarthQuakeRuleByRuleID(int rule_id);

    public EarthquakeRule getEarthQuakeRuleByEarthMagnitude(double magnitude);

    public EarthquakeRule getEarthQuakeRuleByDeathPeople(int death_people);

    public EarthquakeRule getEarthQuakeRuleByEconomyLoss(double economy_loss);

    public EarthquakeRule getEarthQuakeRuleByEarthID(int earth_id);
}
