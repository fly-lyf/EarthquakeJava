package earthquake.site.dao;

import earthquake.site.entity.EarthquakeLeftbar;
import earthquake.site.forms.EmptyForm;
import org.springframework.stereotype.Repository;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class LeftBarRepository extends GenericJpaBaseRepository<Integer, EarthquakeLeftbar, EmptyForm> {

}
