package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by frankstar on 2017/6/9.
 */
@Repository("earthQuakeRuleDAO")
public class EarthquakeRuleDAOImpl implements EarthquakeRuleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static RowMapper<EarthquakeRule> EARTH_QUAKE_RULE_ROW_MAPPER = BeanPropertyRowMapper.newInstance(EarthquakeRule.class);

    @Override
    public List<EarthquakeRule> getAllEarthQuakeRule() {

        List<EarthquakeRule> earthQuakeRuleList;
        String sql = "select * from earth_rule";
        earthQuakeRuleList = jdbcTemplate.query(sql, EARTH_QUAKE_RULE_ROW_MAPPER);

        return earthQuakeRuleList;
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByRuleID(int rule_id) {

        final EarthquakeRule earthquakeRule = new EarthquakeRule();
        String sql = "select * from eath_rule where rule_id = " + rule_id;
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                earthquakeRule.setRuleId(resultSet.getInt("rule_id"));
                earthquakeRule.setEconomyLoss(resultSet.getDouble("economy_loss"));
                earthquakeRule.setMaxDeath(resultSet.getInt("max_death"));
                earthquakeRule.setMinDeath(resultSet.getInt("min_death"));
                earthquakeRule.setMaxMagnitude(resultSet.getDouble("max_magnitude"));
                earthquakeRule.setMinMagnitude(resultSet.getDouble("min_magnitude"));
                earthquakeRule.setRuleTitle(resultSet.getString("rule_title"));
            }
        });
        return earthquakeRule;
    }

    /*@Override
    public EarthquakeRule getEarthQuakeRuleByEarthMagnitude(double magnitude) {
        return null;
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByDeathPeople(int death_people) {
        return null;
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByEconomyLoss(double economy_loss) {
        return null;
    }

    @Override
    public EarthquakeRule getEarthQuakeRuleByEarthID(int earth_id) {
        return null;
    }*/
}
