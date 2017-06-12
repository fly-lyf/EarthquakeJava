package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRuleRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by frankstar on 2017/6/10.
 */
@Repository("earthRuleRespondDAO")
public class EarthquakeRuleRespondDAOImpl implements EarthquakeRuleRespondDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static RowMapper<EarthquakeRuleRespond> EARTH_RULE_RESPOND_ROW_MAPPER = BeanPropertyRowMapper.newInstance(EarthquakeRuleRespond.class);

    @Override
    public List<EarthquakeRuleRespond> getAllEarthRuleRespond() {

        List<EarthquakeRuleRespond> earthRuleRespondList;
        String sql = "select * from earth_rule_respond";
        earthRuleRespondList = jdbcTemplate.query(sql, EARTH_RULE_RESPOND_ROW_MAPPER);

        return earthRuleRespondList;
    }

    @Override
    public int getRespondIDByRuleID(int rule_id) {

        Integer respond_id;
        String sql = "select respond_id from earth_rule_respond where rule_id = " + rule_id;
        respond_id = jdbcTemplate.queryForObject(sql, Integer.TYPE);

        return respond_id;
    }

    @Override
    public int getRuleIDByRespondID(int respond_id) {

        Integer rule_id;
        String sql = "select rule_Id from earth_rule_respond where respond_id = " + respond_id;
        rule_id = jdbcTemplate.queryForObject(sql, Integer.TYPE);

        return rule_id;
    }
}
