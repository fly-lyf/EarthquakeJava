package earthquake.site.repositories.impls;

import earthquake.site.entities.EarthquakeLeftbar;
import earthquake.site.repositories.GenericJpaBaseRepository;
import earthquake.site.repositories.interfaces.LeftBarRepository;
import earthquake.site.repositories.interfaces.WebpagesRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class LeftBarRepositoryImpl extends GenericJpaBaseRepository<String, EarthquakeLeftbar> implements LeftBarRepository {

}
