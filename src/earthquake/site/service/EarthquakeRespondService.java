package earthquake.site.service;

import earthquake.site.entity.EarthquakeRespond;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frankstar on 2017/6/10.
 */
@Service
public interface EarthquakeRespondService {

    public List<EarthquakeRespond> getAllEarthQuakeRespond();

    public EarthquakeRespond getEarthQuakeRespondByRuleID(int rule_id);

    public EarthquakeRespond getEarthQuakeRespondByEarthID(int earth_id);

    public EarthquakeRespond getEarthQuakeRespondByMagnitude(double magnitude);

    public EarthquakeRespond getEarthQuakeRespondByDeathPeople(int death_people);

    public EarthquakeRespond getEarthQuakeRespondByEconomyLoss(double economy_loss);

}
