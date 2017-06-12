package earthquake.site.service;

import earthquake.site.dao.EarthquakeInfoDAO;
import earthquake.site.entity.EarthquakeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
@Service
public class EarthquakeInfoServiceImpl implements EarthquakeInfoService {

    @Autowired
    private EarthquakeInfoDAO earthquakeInfoDAO;

    @Override
    public List<EarthquakeInfo> getAllEarthQuakeInfo(int pageNumber) {

        List<EarthquakeInfo> earthQuakeList = earthquakeInfoDAO.getAllEarthQuake(pageNumber);
        return earthQuakeList;
    }

    @Override
    public EarthquakeInfo getEarthQuakeInfo(int earth_id) {

        EarthquakeInfo earthquakeInfo = earthquakeInfoDAO.getEarthQuakeByID(earth_id);
        return earthquakeInfo;
    }
}
