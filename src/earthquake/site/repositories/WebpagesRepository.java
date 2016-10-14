package earthquake.site.repositories;
import earthquake.site.forms.SearchForm;
import earthquake.site.entities.EarthquakeWebpages;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class WebpagesRepository extends GenericJpaBaseRepository<Integer, EarthquakeWebpages> {

    public Iterable<EarthquakeWebpages> getByCondition(SearchForm form) {
        String query = "select entity from EarthquakeWebpages entity";
        boolean firstFlag = true;
//        for (Iterator<Map.Entry<String, String>> iterator = conditions.entrySet().iterator(); iterator.hasNext(); ) {
//            Map.Entry<String, String> condition = iterator.next();
//            String key = condition.getKey();
//            if (firstFlag) {
//                firstFlag = false;
//                query += " where ";
//            } else {
//                query += " and ";
//            }
//            if (key.equals("startTime")) {
//                query += "entity.crawledTime > " + condition.getValue();
//            } else if (key.equals("endTime")) {
//                query += "entity.crawledTime < " + condition.getValue();
//            } else if (key.equals("title") || key.equals("content") || key.equals("h1") || key.equals("h2")) {
//                query += "entity." + key + " like %" + condition.getValue() + "%";
//            } else if (key.equals("topic")) {
//                query += "entity.topic = " + condition.getValue();
//            }
//        }
        System.out.println(query);
        TypedQuery<EarthquakeWebpages> typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }
}
