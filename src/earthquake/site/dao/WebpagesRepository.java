package earthquake.site.dao;

import earthquake.site.forms.WebpageSearchForm;
import earthquake.site.entity.EarthquakeWebpages;
import earthquake.site.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class WebpagesRepository extends GenericJpaBaseRepository<Integer, EarthquakeWebpages> {

    @Inject
    private CommonService<WebpageSearchForm> commonService;
    private static final Logger log = LogManager.getLogger();

    public List getWebpagesByCondition(WebpageSearchForm form) {
        if (form.pageCount == null || form.pageCount.equals("")) {
            form.pageCount = "1";
        }
        if (form.pageNum == null || form.pageNum.equals("")) {
            form.pageNum = "10";
        }
        TypedQuery typedQuery = getWebpagesQuery(form);
        return typedQuery.getResultList();
    }

    public long getCount(WebpageSearchForm form) {
        form.pageCount = "";
        form.pageNum = "";
        TypedQuery typedQuery = getWebpagesQuery(form);
        return typedQuery.getResultList().size();
    }

    public TypedQuery getWebpagesQuery(WebpageSearchForm form) {
        String query = "select entity from EarthquakeWebpages entity";
        DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");

        HashMap<String, Object> attrsMap = commonService.formMap(form);
//        Field[] fields = form.getClass().getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            String attrname = field.getName();
//            try {
//                Object value = field.get(form);
//                if (value != null) {
//                    attrsMap.put(attrname, value);
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

        ArrayList<String> subQuery = new ArrayList<>();

        for (Map.Entry<String, Object> entry : attrsMap.entrySet()) {
            String key = entry.getKey();
            switch (key) {
                case "crawledStartTime":
                    subQuery.add("entity.crawledTime > " + format.format(entry.getValue()));
                    break;
                case "crawledEndTime":
                    subQuery.add("entity.crawledTime < " + format.format(entry.getValue()));
                    break;
                case "publishedStartTime":
                    subQuery.add("entity.publishedTime > " + format.format(entry.getValue()));
                    break;
                case "publishedEndTime":
                    subQuery.add("entity.publishedTime < " + format.format(entry.getValue()));
                    break;

                case "title":
                case "content":
                case "summary":
                    subQuery.add("entity." + key + " like '%" + entry.getValue() + "%'");

                    break;
                case "eventId":
                case "typeName":
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
        attrsMap.putIfAbsent("orderName", "crawledTime");
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
