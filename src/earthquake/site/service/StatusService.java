package earthquake.site.service;
import earthquake.site.dao.*;
import earthquake.site.entity.*;
import org.springframework.stereotype.Service;
import earthquake.site.dao.StatusRepository;
import earthquake.site.dao.InfoRepository;
import earthquake.site.dao.RespondRepository;
import earthquake.site.dao.RuleRespondRepository;
import earthquake.site.dao.LossRepository;


import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
@Service
public class StatusService {
    @Inject
    private InfoRepository infoRepository;
    @Inject
    private RuleRespondRepository ruleRespondRepository;
    @Inject
    private RespondRepository respondRepository;
    @Inject
    private LossRepository lossRepository;
    @Inject
    private StatusRepository statusRepository;

    // 获得响应等级
    public List<EarthquakeRespond> getRespond(String id){
        // 获得震级和死亡人数，loss信息可能没有
        List<EarthquakeInfo> earthquakeInfos = infoRepository.getByEventId(id);
        List<EarthquakeLoss> earthquakeLosses = lossRepository.getLossByEventId(id);
        Double magnitude = earthquakeInfos.get(0).getMagnitude();
        if(magnitude == null){
            magnitude = 0.0;
        }
        int ruleId = 3; //如果没有默认为最低紧急程度
        // 获取ruleId
        if(earthquakeLosses.size()!=1){
            ruleId = statusRepository.getRuleByMagnitude(magnitude);
        }else {
            EarthquakeLoss earthquakeLoss = earthquakeLosses.get(0);
            Integer death = earthquakeLoss.getDeath();
            if(death == null){
                death = 0;
            }
            ruleId = statusRepository.getRuleByEventId(death,magnitude);
            System.out.print("ruleId"+ruleId);
        }
        int respondId = ruleRespondRepository.getRespondIdByRuleId(ruleId);
        System.out.print("respondId"+respondId);

        List<EarthquakeRespond> responds = respondRepository.getRespondById(respondId);
        return  responds;
    }
}
