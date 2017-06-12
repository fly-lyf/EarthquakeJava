package earthquake.site.service;

import earthquake.site.dao.EarthquakeLossDAO;
import earthquake.site.entity.EarthquakeLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by frankstar on 2017/6/10.
 */
@Service
public class EarthquakeLossServiceImpl implements EarthquakeLossService {

    @Autowired
    private EarthquakeLossDAO EarthquakeLossDAO;

    @Override
    public EarthquakeLoss getEarthquakeLossByEarthID(int earth_id) {

        EarthquakeLoss EarthquakeLoss = EarthquakeLossDAO.getEarthQuakeLossByEarthID(earth_id);
        return EarthquakeLoss;
    }

    @Override
    public void addEarthquakeLoss(int earth_id, int death_people, int injury, double economy_loss, double destroy) {

        EarthquakeLossDAO.addEarthQuakeLoss(earth_id, death_people, injury, economy_loss, destroy);

    }
}
