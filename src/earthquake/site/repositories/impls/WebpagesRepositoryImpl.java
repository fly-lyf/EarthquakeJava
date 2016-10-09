package earthquake.site.repositories.impls;

import earthquake.site.entities.EarthquakeWebpages;
import earthquake.site.repositories.GenericJpaBaseRepository;
import earthquake.site.repositories.interfaces.UrlsRepository;
import earthquake.site.repositories.interfaces.WebpagesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class WebpagesRepositoryImpl extends GenericJpaBaseRepository<String, EarthquakeWebpages> implements WebpagesRepository {

    public Iterable<EarthquakeWebpages> getByCondition(Map<String, String> conditions) {
        String query = "select entity from EarthquakeWebpages entity";
        boolean firstFlag = true;
        for (Iterator<Map.Entry<String, String>> iterator = conditions.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> condition = iterator.next();
            String key = condition.getKey();
            if (firstFlag) {
                firstFlag = false;
                query += " where ";
            } else {
                query += " and ";
            }
            if (key.equals("startTime")) {
                query += "entity.crawledTime > " + condition.getValue();
            } else if (key.equals("endTime")) {
                query += "entity.crawledTime < " + condition.getValue();
            } else if (key.equals("title") || key.equals("content") || key.equals("h1") || key.equals("h2")) {
                query += "entity." + key + " like %" + condition.getValue() + "%";
            } else if (key.equals("topic")) {
                query += "entity.topic = " + condition.getValue();
            }
        }
        System.out.println(query);
        TypedQuery<EarthquakeWebpages> typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }
}
