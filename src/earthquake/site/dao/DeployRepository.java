package earthquake.site.dao;

/**
 * Created by Administrator on 2017/7/1.
 */

import earthquake.site.entity.EarthquakeDeployEntity;
import earthquake.site.forms.BriefSearchForm;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DeployRepository extends GenericJpaBaseRepository<Integer, EarthquakeDeployEntity, BriefSearchForm>{
    /**
     * 根据响应等级id及所在省份获取相应的处理方案
     * */
    public List<EarthquakeDeployEntity> getDeploy(int id, String province){
        if(province.indexOf("省")!=-1){
            province = province.substring(0,province.indexOf("省"));
        }
        String query = "select entity from EarthquakeDeployEntity entity where entity.respondId=" + id + "and entity.province='" + province +"'";
        TypedQuery<EarthquakeDeployEntity> typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }

}
