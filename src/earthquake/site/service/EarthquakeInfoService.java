package earthquake.site.service;

import earthquake.site.entity.EarthquakeInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
@Service
public interface EarthquakeInfoService {

    public List<EarthquakeInfo> getAllEarthQuakeInfo(int pageNumber);

    public EarthquakeInfo getEarthQuakeInfo(int erath_id);



}
