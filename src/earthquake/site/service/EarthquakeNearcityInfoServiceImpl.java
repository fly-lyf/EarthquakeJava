package earthquake.site.service;

import earthquake.site.dao.EarthquakeNearcityDAO;
import earthquake.site.entity.EarthquakeRelation;
import earthquake.site.entity.EarthquakeNearcityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
@Service
public class EarthquakeNearcityInfoServiceImpl implements EarthquakeNearcityInfoService {


    @Autowired
    private EarthquakeNearcityDAO earthquakeNearcityDAO;

    @Override
    public void addEarthQuakeNearCityInfo(int earth_id, int earth_near) {

        earthquakeNearcityDAO.addEarthQuakeNearCity(earth_id, earth_near);

    }

    @Override
    public List<EarthquakeNearcityInfo> getEarthQuakeNearCity(int earth_id) {

        List<EarthquakeRelation> earthRelationList = earthquakeNearcityDAO.getEarthRelationByEarthID(earth_id);
        List<Integer> nearCityId = new ArrayList<>();
        for (EarthquakeRelation e: earthRelationList) {

            nearCityId.add(e.getEarthNear());
        }
        List<EarthquakeNearcityInfo> nearCityList = earthquakeNearcityDAO.getNearCityInfoByEarthNearID(nearCityId);

        return nearCityList;
    }
}
