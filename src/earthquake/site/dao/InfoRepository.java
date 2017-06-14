package earthquake.site.dao;

import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.forms.BriefSearchForm;
import earthquake.site.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
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
public class InfoRepository extends GenericJpaBaseRepository<Integer, EarthquakeInfo> {

    @Inject
    private CommonService<BriefSearchForm> commonService;
    private static final Logger log = LogManager.getLogger();

    //根据发生地、时间等查询地震基本信息
    public List<EarthquakeInfo> getEarthquakeInfoByCondition(BriefSearchForm briefSearchForm) {
        return getEarthquakeInfoQuery(briefSearchForm).getResultList();
    }


    public TypedQuery<EarthquakeInfo> getEarthquakeInfoQuery(BriefSearchForm briefSearchForm) {

        String query = "select entity from EarthquakeInfo entity";

        DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> attrsMap = commonService.formMap(briefSearchForm);

        //拼接查询条件
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

                case "location":
                    subQuery.add("entity." + key + " like '%" + entry.getValue() + "%'");
                    break;

                case "eventId":
                case "magnitude":
                    subQuery.add("entity." + key + "=" + entry.getValue());
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
        attrsMap.putIfAbsent("orderName", "earthquakeTime");
        String sqlOrder = "";
        if (attrsMap.get("order").equals("2")) {
            sqlOrder = "desc";
        }
        query += " order by entity." + attrsMap.get("orderName") + " " + sqlOrder;

        log.info(query);
        System.out.println(query);
        TypedQuery<EarthquakeInfo> typedQuery = entityManager.createQuery(query, entityClass);

        return typedQuery;
    }

}
