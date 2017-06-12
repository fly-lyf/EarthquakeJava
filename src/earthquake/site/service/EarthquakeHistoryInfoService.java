package earthquake.site.service;

import earthquake.site.entity.EarthquakeInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
@Service
public interface EarthquakeHistoryInfoService {

    public void addEarthQuakeHistory(int earth_id, int earth_history);

    public List<EarthquakeInfo> getEarthQuakeHistoryInfo(int earth_id);
}
