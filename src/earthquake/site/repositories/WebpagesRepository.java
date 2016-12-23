package earthquake.site.repositories;

import earthquake.site.forms.SearchForm;
import earthquake.site.entities.EarthquakeWebpages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class WebpagesRepository extends GenericJpaBaseRepository<Integer, EarthquakeWebpages> {

    private static final Logger log = LogManager.getLogger();

    public List getByCondition(SearchForm form) {
        if (form.pageCount == null || form.pageCount.equals("")) {
            form.pageCount = "1";
        }
        if (form.pageNum == null || form.pageNum.equals("")) {
            form.pageNum = "10";
        }
        TypedQuery typedQuery = getQuery(form);
        return typedQuery.getResultList();
    }

    public long getCount(SearchForm form) {
        form.pageCount = "";
        form.pageNum = "";
        TypedQuery typedQuery = getQuery(form);
        return typedQuery.getResultList().size();
    }

    public TypedQuery getQuery(SearchForm form) {
        String query = "select entity from EarthquakeWebpages entity";
        DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");

        HashMap<String, Object> attrsMap = new HashMap<>();
        Field[] fields = form.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String attrname = field.getName();
            try {
                Object value = field.get(form);
                if (value != null) {
                    attrsMap.put(attrname, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        ArrayList<String> subQuery = new ArrayList<>();

        for (Map.Entry<String, Object> entry : attrsMap.entrySet()) {
            String key = entry.getKey();
            switch (key) {
                case "crawledStartTime":
                    subQuery.add("entity.crawledtime > " + format.format(entry.getValue()));
                    break;
                case "crawledEndTime":
                    subQuery.add("entity.crawledtime < " + format.format(entry.getValue()));
                    break;
                case "publishedStartTime":
                    subQuery.add("entity.publishedtime > " + format.format(entry.getValue()));
                    break;
                case "publishedEndTime":
                    subQuery.add("entity.publishedtime < " + format.format(entry.getValue()));
                    break;

                case "title":
                case "content":
                case "summary":
                    subQuery.add("entity." + key + " like '%" + entry.getValue() + "%'");

                    break;
                case "eventid":
                case "typename":
                    subQuery.add("entity." + key + "='" + entry.getValue() + "'");
                    break;
            }
        }

        for (int i = 0; i < subQuery.size(); i++) {
            String sub = subQuery.get(i);
            if (i == 0) {
                query = query + " where " + sub;
            } else {
                query = query + " and " + sub;
            }
        }

        attrsMap.putIfAbsent("order", "2");
        attrsMap.putIfAbsent("orderName", "crawledtime");
        String sqlOrder = "";
        if (attrsMap.get("order").equals("2")) {
            sqlOrder = "desc";
        }
        query += " order by entity." + attrsMap.get("orderName") + " " + sqlOrder;

        log.info(query);
        System.out.println(query);
        TypedQuery<EarthquakeWebpages> typedQuery = entityManager.createQuery(query, entityClass);

        if (!attrsMap.get("pageCount").equals("")) {
            Integer pageCount = Integer.parseInt((String) attrsMap.get("pageCount"));
            Integer pageNum = Integer.parseInt((String) attrsMap.get("pageNum"));
            typedQuery.setFirstResult((pageCount - 1) * pageNum).setMaxResults(pageNum);
        }

        return typedQuery;
    }
}
