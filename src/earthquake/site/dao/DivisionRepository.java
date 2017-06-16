package earthquake.site.dao;

import earthquake.site.entity.EarthquakeAdministrativeDivision;
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
public class DivisionRepository extends GenericJpaBaseRepository<Integer, EarthquakeAdministrativeDivision, BriefSearchForm> {

    //查询区划信息
    public List<EarthquakeAdministrativeDivision> getAdministrativeDivisionByCondition(BriefSearchForm briefSearchForm) {

        HashMap<String, Object> attrsMap = getConditionMap(briefSearchForm);

        //拼接查询条件
        ArrayList<String> subQuery = new ArrayList<>();
        for (Map.Entry<String, Object> entry : attrsMap.entrySet()) {
            String key = entry.getKey();
            switch (key) {
                case "province":
                case "city":
                case "county":
                    if(!entry.getValue().equals("")){
                        subQuery.add("entity." + key + " like '%" + entry.getValue() + "%'");
                    }
                    break;
            }
        }

        TypedQuery<EarthquakeAdministrativeDivision> query = getTypedQuery(subQuery, attrsMap);
        return query.getResultList();
    }

}
