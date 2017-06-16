package earthquake.site.dao;

import earthquake.site.forms.WebpageSearchForm;
import earthquake.site.entity.EarthquakeWebpages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class WebpagesRepository extends GenericJpaBaseRepository<Integer, EarthquakeWebpages, WebpageSearchForm> {

    public List getWebpagesByCondition(WebpageSearchForm form) {
        if (form.pageCount == null || form.pageCount.equals("")) {
            form.pageCount = "1";
        }
        if (form.pageNum == null || form.pageNum.equals("")) {
            form.pageNum = "10";
        }

        HashMap<String, Object> attrsMap = getConditionMap(form);
        ArrayList<String> subQuery = getSubQuery(attrsMap);
        TypedQuery<EarthquakeWebpages> typedQuery = getTypedQuery(subQuery, attrsMap);
        return typedQuery.getResultList();
    }

    public long getCount(WebpageSearchForm form) {
        form.pageCount = "";
        form.pageNum = "";
        HashMap<String, Object> attrsMap = getConditionMap(form);
        ArrayList<String> subQuery = getSubQuery(attrsMap);
        TypedQuery typedQuery = getTypedQuery(subQuery, attrsMap);
        return typedQuery.getResultList().size();
    }

    //构造HQL语句文本
    public ArrayList<String> getSubQuery(HashMap<String, Object> attrsMap){
        DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
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
        return subQuery;
    }

}
