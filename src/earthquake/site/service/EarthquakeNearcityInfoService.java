package earthquake.site.service;

import earthquake.site.entity.EarthquakeNearcityInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
@Service
public interface EarthquakeNearcityInfoService {

    public void addEarthQuakeNearCityInfo(int earth_id, int earth_near);

    public List<EarthquakeNearcityInfo> getEarthQuakeNearCity(int earth_id);

}

