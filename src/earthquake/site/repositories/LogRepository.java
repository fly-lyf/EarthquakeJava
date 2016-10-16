package earthquake.site.repositories;

import earthquake.site.entities.EarthquakeLog;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fly on 2016/10/15.
 */

@Repository
public class LogRepository extends GenericJpaBaseRepository<Integer, EarthquakeLog> {

    public Iterable<EarthquakeLog> getTwoMinutes() {
        Date twoMinutes = new Date(new Date().getTime() - 60000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(twoMinutes);
        String query = "from EarthquakeLog log where log.time >= :date order by log.time desc";
        Query typedQuery = entityManager.createQuery(query, entityClass);
        typedQuery.setParameter("date", twoMinutes);
        return typedQuery.getResultList();
    }
}
