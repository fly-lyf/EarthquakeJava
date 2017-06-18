package earthquake.site.dao;

import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.forms.BriefSearchForm;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class InfoRepository extends GenericJpaBaseRepository<Integer, EarthquakeInfo, BriefSearchForm> {

    public List<EarthquakeInfo> getByEventId(String eventId) {
        String query = "select entity from EarthquakeInfo entity where entity.eventId='" + eventId + "'";
        System.out.println(query);
        TypedQuery<EarthquakeInfo> typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }


    //根据发生地、时间等查询地震基本信息
    public List<EarthquakeInfo> getEarthquakeInfoByCondition(BriefSearchForm briefSearchForm) {
        HashMap<String, Object> attrsMap = getConditionMap(briefSearchForm);

        //拼接查询条件
        DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<String> subQuery = new ArrayList<>();
        for (Map.Entry<String, Object> entry : attrsMap.entrySet()) {
            String key = entry.getKey();
            switch (key) {
                case "earthquakeTimeStart":
                    subQuery.add("entity.earthquakeTime > " + format.format(entry.getValue()));
                    break;
                case "earthquakeTimeEnd":
                    subQuery.add("entity.earthquakeTime < " + format.format(entry.getValue()));
                    break;

                case "province":
                case "city":
                case "county":
                    if (!entry.getValue().equals("") && entry.getValue() != null) {
                        subQuery.add("entity." + key + " like '%" + entry.getValue() + "%'");
                    }
                    break;
                case "eventId":
                    subQuery.add("entity." + key + "='" + entry.getValue() + "'");
                    break;
                case "magnitude":
                    subQuery.add("entity." + key + "=" + entry.getValue());
                    break;
            }
        }

        TypedQuery<EarthquakeInfo> query = getTypedQuery(subQuery, attrsMap, "earthquakeTime", "2");
        return query.getResultList();
    }


}
