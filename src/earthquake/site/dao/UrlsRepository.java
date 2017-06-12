package earthquake.site.dao;

import earthquake.site.entity.EarthquakeUrls;
import org.springframework.stereotype.Repository;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class UrlsRepository extends GenericJpaBaseRepository<Integer, EarthquakeUrls> {

}
