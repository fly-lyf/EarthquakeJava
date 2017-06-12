package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRespond;
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
@Repository("earthQuakeRespondDAO")
public class EarthquakeRespondDAOImpl implements EarthquakeRespondDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static RowMapper<EarthquakeRespond> EARTH_QUAKE_RESPOND_ROW_MAPPER = BeanPropertyRowMapper.newInstance(EarthquakeRespond.class);

    @Override
    public List<EarthquakeRespond> getAllEarthQuakeRespond() {

        List<EarthquakeRespond> earthQuakeRespondList = new ArrayList<>();
        String sql = "select * from earth_respond";
        earthQuakeRespondList = jdbcTemplate.query(sql, EARTH_QUAKE_RESPOND_ROW_MAPPER);

        return earthQuakeRespondList;
    }

    @Override
    public EarthquakeRespond getEarthQuakeRespondByRespondID(int respond_id) {

        String sql = "select * from earth_respond where respond_id = " + respond_id;
        final EarthquakeRespond earthQuakeRespond = new EarthquakeRespond();

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                earthQuakeRespond.setRespondId(resultSet.getInt("respond_id"));
                earthQuakeRespond.setRespondLead(resultSet.getString("respond_lead"));
                earthQuakeRespond.setRespondOrganize(resultSet.getString("respond_organize"));
                earthQuakeRespond.setRespondTitle(resultSet.getString("respond_title"));
            }
        });


        return earthQuakeRespond;
    }

    /*@Override
    public EarthquakeRespond getEarthQuakeRespondByEarthID(int earth_id) {
        return null;
    }*/
}
