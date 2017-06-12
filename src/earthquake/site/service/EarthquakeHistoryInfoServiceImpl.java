package earthquake.site.service;

import earthquake.site.dao.EarthquakeInfoDAO;
import earthquake.site.dao.EarthQuakeHistoryDAO;
import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.entity.EarthquakeRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
@Service
public class EarthquakeHistoryInfoServiceImpl implements EarthquakeHistoryInfoService {

    @Autowired
    private EarthQuakeHistoryDAO earthQuakeHistoryDAO;

    @Autowired
    private EarthquakeInfoDAO earthquakeInfoDAO;

    @Override
    public void addEarthQuakeHistory(int earth_id, int earth_history) {

        earthQuakeHistoryDAO.addEarthQuakeHistory(earth_id, earth_history);
    }

    @Override
    public List<EarthquakeInfo> getEarthQuakeHistoryInfo(int earth_id) {

        List<EarthquakeRelation> earthRelationList = earthQuakeHistoryDAO.getEarthRelationByEarthId(earth_id);
        List<Integer> earthQuakeHistoryID = new ArrayList<>();
        for (EarthquakeRelation e: earthRelationList) {
            earthQuakeHistoryID.add(e.getEarthHistory());
        }
        List<EarthquakeInfo> earthQuakeList = earthquakeInfoDAO.getEarthQuakeByID(earthQuakeHistoryID);

        return earthQuakeList;
    }
}
