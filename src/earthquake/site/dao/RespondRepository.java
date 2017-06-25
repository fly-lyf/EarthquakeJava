package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRespond;
import earthquake.site.forms.StatusSearchForm;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
@Repository
public class RespondRepository extends GenericJpaBaseRepository<Integer, EarthquakeRespond, StatusSearchForm> {
    /**
     * 获取响应等级条目
     * @param id 相应等级id
     * @return responds 一般来说只有一条结果
     * */
    public List<EarthquakeRespond> getRespondById(int id){
        String query = "select entity from EarthquakeRespond entity where entity.respondId=" + id;
        TypedQuery<EarthquakeRespond> typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }
}
