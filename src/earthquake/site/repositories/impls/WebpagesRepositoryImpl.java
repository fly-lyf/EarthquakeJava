package earthquake.site.repositories.impls;

import earthquake.site.entities.EarthquakeWebpages;
import earthquake.site.repositories.GenericJpaBaseRepository;
import earthquake.site.repositories.interfaces.UrlsRepository;
import earthquake.site.repositories.interfaces.WebpagesRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class WebpagesRepositoryImpl extends GenericJpaBaseRepository<String, EarthquakeWebpages> implements WebpagesRepository {

}
