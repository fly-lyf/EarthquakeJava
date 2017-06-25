package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRespond;
import earthquake.site.entity.EarthquakeRule;
import earthquake.site.entity.EarthquakeRuleRespond;
import earthquake.site.forms.StatusSearchForm;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/25.
 */
@Repository
public class RuleRespondRepository extends GenericJpaBaseRepository<Integer, EarthquakeRuleRespond, StatusSearchForm> {
    /**
     * 根据规则响应等级关联表获得响应id
     * @param ruleId
     * @return respondId
     * */
    public int getRespondIdByRuleId(int ruleId){
        ArrayList<String> subQuery = new ArrayList<>();
        subQuery.add("entity.ruleId = "+ruleId);
        TypedQuery<EarthquakeRuleRespond> query = getTypedQuery(subQuery);
        return query.getResultList().get(0).getRespondId();
    }
}
