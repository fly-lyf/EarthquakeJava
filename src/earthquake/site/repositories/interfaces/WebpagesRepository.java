package earthquake.site.repositories.interfaces;

import earthquake.site.entities.EarthquakeWebpages;
import earthquake.site.repositories.GenericRepository;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by earthquake on 2016/10/8.
 */

public interface WebpagesRepository extends GenericRepository<String, EarthquakeWebpages> {



}
