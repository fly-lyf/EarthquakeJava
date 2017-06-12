package earthquake.site.service;

import earthquake.site.entity.EarthquakeLoss;
import org.springframework.stereotype.Service;

/**
 * Created by frankstar on 2017/6/10.
 */
@Service
public interface EarthquakeLossService {

    public EarthquakeLoss getEarthquakeLossByEarthID(int earth_id);

    public void addEarthquakeLoss(int earth_id, int death_people, int injury, double economy_loss, double destroy);

}
