package earthquake.site.repositories;

import earthquake.site.entities.EarthquakeUrls;
import org.springframework.stereotype.Repository;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class UrlsRepository extends GenericJpaBaseRepository<String, EarthquakeUrls> {

}
