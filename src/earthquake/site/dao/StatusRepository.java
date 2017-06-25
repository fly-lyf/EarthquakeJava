package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRule;
import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.entity.EarthquakeLoss;
import earthquake.site.forms.StatusSearchForm;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/24.
 */
@Repository
public class StatusRepository extends GenericJpaBaseRepository<Integer, EarthquakeRule, StatusSearchForm>{
    @Inject
    private InfoRepository infoRepository;
    @Inject
    private LossRepository lossRepository;
    // 直接查询地震紧急程度
    public List<EarthquakeRule> getStatusByCondition(StatusSearchForm statusSearchForm){
        // 查询字段
        HashMap<String, Object> queryMap = getConditionMap(statusSearchForm);
        // 拼接的查询子字段
        ArrayList<String> subQuery = new ArrayList<>();
        for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
            String key = entry.getKey();
            switch (key){
                case "death":
                    subQuery.add("entity.minDeath < " + entry.getValue());
                    subQuery.add("entity.maxDeath > " + entry.getValue());
                    break;
                case "magnitude":
                    subQuery.add("entity.maxMagnitude > " + entry.getValue());
                    subQuery.add("entity.minMagnitude < " + entry.getValue());
                    break;
            }
        }
        TypedQuery<EarthquakeRule> query = getTypedQuery(subQuery, queryMap);
        return query.getResultList();
    }

    public List<EarthquakeRule> getByEventId(String id){
        List<EarthquakeInfo> earthquakeInfos = infoRepository.getByEventId(id);
        List<EarthquakeLoss> earthquakeLosses = lossRepository.getLossByEventId(id);
        EarthquakeLoss earthquakeLoss = earthquakeLosses.get(0);
        Int death = earthquakeLoss.getDeath();
        Double magnitude = earthquakeInfos.get(0).getMagnitude();

        HashMap<String, Object> queryMap = new HashMap<>();
        ArrayList<String> subQuery = new ArrayList<>();
        subQuery.add("entity.minDeath < " + death);
        subQuery.add("entity.maxDeath > " + death);
        subQuery.add("entity.maxMagnitude > " + magnitude);
        subQuery.add("entity.minMagnitude < " + magnitude);
        TypedQuery<EarthquakeRule> query = getTypedQuery(subQuery,queryMap);
        return query.getResultList();

    }
}
