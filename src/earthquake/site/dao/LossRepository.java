package earthquake.site.dao;

import earthquake.site.entity.EarthquakeLoss;
import earthquake.site.forms.BriefSearchForm;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
public class LossRepository extends GenericJpaBaseRepository<Integer, EarthquakeLoss,BriefSearchForm> {
    public List<EarthquakeLoss> getLossByEventId(String eventId){
        String query = "select entity from EarthquakeLoss entity where entity.eventId='" + eventId + "'";
        TypedQuery<EarthquakeLoss> typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }
}
