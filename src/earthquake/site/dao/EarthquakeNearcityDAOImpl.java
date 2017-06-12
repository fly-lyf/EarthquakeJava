package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRelation;
import earthquake.site.entity.EarthquakeNearcityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frankstar on 2017/6/9.
 */
@Repository("earthQuakeNearCityDAO")
public class EarthquakeNearcityDAOImpl implements EarthquakeNearcityDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static RowMapper<EarthquakeRelation> EARTH_RELATION_ROW_MAPPER = BeanPropertyRowMapper.newInstance(EarthquakeRelation.class);

    @Override
    public void addEarthQuakeNearCity(int earth_id, int earth_near) {

        String sql = "insert into earht_relation (earth_id, earth_near) values (" + earth_id + "," + earth_near + ")";

        jdbcTemplate.update(sql);
    }

    @Override
    public List<EarthquakeRelation> getEarthRelationByEarthID(int earth_id) {

        List<EarthquakeRelation> earthRelationList = new ArrayList<>();
        String sql = "select * from earth_relation where earth_id =" + earth_id;

        earthRelationList = jdbcTemplate.query(sql, EARTH_RELATION_ROW_MAPPER);

        return earthRelationList;

    }

    @Override
    public List<EarthquakeNearcityInfo> getNearCityInfoByEarthNearID(List<Integer> earthNear) {

        List<EarthquakeNearcityInfo> nearCityList = new ArrayList<>();
        for (Integer e : earthNear) {
            final EarthquakeNearcityInfo nearCity = new EarthquakeNearcityInfo();
            String sql = "select * from nearcity_info where nearcity_id = " + e;
            jdbcTemplate.query(sql, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    nearCity.setNearcityId(resultSet.getInt("nearcity_id"));
                    nearCity.setCity(resultSet.getString("city"));
                    nearCity.setDistance(resultSet.getDouble("distance"));
                }
            });
            nearCityList.add(nearCity);
        }

        return nearCityList;
    }

    @Override
    public EarthquakeNearcityInfo getNearCityInfoByEarthNearID(int earthNear) {

        final EarthquakeNearcityInfo nearCity = new EarthquakeNearcityInfo();
        String sql = "select * from nearcity_info where nearcity_id = " + earthNear;

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                nearCity.setDistance(resultSet.getDouble("distance"));
                nearCity.setCity(resultSet.getString("city"));
                nearCity.setNearcityId(resultSet.getInt("nearcity_id"));
            }
        });

        return nearCity;
    }
}
