package earthquake.site.service;

import earthquake.site.dao.DeployRepository;
import earthquake.site.dao.InfoRepository;
import earthquake.site.entity.EarthquakeDeployEntity;
import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.entity.EarthquakeRespond;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */
@Service
public class RespondService {
    @Inject
    private InfoRepository infoRepository;
    @Inject
    private StatusService statusService;
    @Inject
    private DeployRepository deployRepository;
    /**
     * 根据事件序列获取地震信息及相应等级从而获取应急方案
     * */
    public List<EarthquakeDeployEntity> getRespondDeploy(String id){
        List<EarthquakeInfo> earthquakeInfos = infoRepository.getByEventId(id);
        String province = earthquakeInfos.get(0).getProvince();
        List<EarthquakeRespond> responds = statusService.getRespond(id);
        int respondId = responds.get(0).getRespondId();
        List<EarthquakeDeployEntity> deployEntities = deployRepository.getDeploy(respondId,province);
        return  deployEntities;
    }
}
