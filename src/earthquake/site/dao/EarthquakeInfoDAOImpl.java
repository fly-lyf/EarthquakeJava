package earthquake.site.dao;

import earthquake.site.entity.EarthquakeInfo;
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
 * Created by frankstar on 2017/6/8.
 */
@Repository("earthQuakeDAO")
public class EarthquakeInfoDAOImpl implements EarthquakeInfoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final static RowMapper<EarthquakeInfo> EARTH_QUAKE_ROW_MAPPER = BeanPropertyRowMapper.newInstance(EarthquakeInfo.class);
    private final static int PAGESIZE = 5;


    @Override
    public List<EarthquakeInfo> getAllEarthQuake(int pageNumber) {

        List<EarthquakeInfo> earthQuakeList = new ArrayList<>();
        String sql = "select * from earth_info ORDER BY earth_time DESC limit " + pageNumber + "," + PAGESIZE;

        earthQuakeList = jdbcTemplate.query(sql, EARTH_QUAKE_ROW_MAPPER);

        return earthQuakeList;
    }

    @Override
    public List<EarthquakeInfo> getEarthQuakeByTime(String time) {

        List<EarthquakeInfo> earthQuakeList;
        String sql = "select * from earth_info where earth_time <= " + time;
        earthQuakeList = jdbcTemplate.query(sql, EARTH_QUAKE_ROW_MAPPER);

        return earthQuakeList;
    }

    @Override
    public List<EarthquakeInfo> getEarthQuakeByCity(String city) {

        List<EarthquakeInfo> earthQuakeList;
        String sql = "select * from earth_info where city LIKE %" + city + "%";
        earthQuakeList = jdbcTemplate.query(sql, EARTH_QUAKE_ROW_MAPPER);

        return earthQuakeList;
    }

    @Override
    public void addEarthQuakeInfo(String earth_title, String earth_time, double longitude, double latitude, int deepth, double magnitude, String city) {

        String sql = "insert into earth_info (earth_title, earth_time, longitude, latitude, deepth, magnitude, city) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{earth_title, earth_time, longitude, latitude, deepth, magnitude, city});
    }

    @Override
    public void deleteEarthQuake(int earth_id) {

        //后续 级联删除

    }

    @Override
    public EarthquakeInfo getEarthQuakeByID(int earth_id) {

        final EarthquakeInfo earthquakeInfo = new EarthquakeInfo();
        String sql = "select * from earth_info where earth_id = " + earth_id;
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                earthquakeInfo.setCity(resultSet.getString("city"));
                earthquakeInfo.setMagnitude(resultSet.getDouble("magnitude"));
                earthquakeInfo.setDeepth(resultSet.getInt("deepth"));
                earthquakeInfo.setLatitude(resultSet.getDouble("latitude"));
                earthquakeInfo.setEarthTime(resultSet.getTimestamp("earth_time"));
                earthquakeInfo.setEarthId(resultSet.getInt("earth_id"));
                earthquakeInfo.setLongitude(resultSet.getDouble("longitude"));
                earthquakeInfo.setEarthTitle(resultSet.getString("earth_title"));
            }
        });
        return earthquakeInfo;
    }

    @Override
    public List<EarthquakeInfo> getEarthQuakeByID(List<Integer> earth_id) {

        List<EarthquakeInfo> earthQuakeList = new ArrayList<>();
        for (Integer i: earth_id ) {
            String sql = "select * from earth_info where earth_id = " + i;
            earthQuakeList.add(jdbcTemplate.queryForObject(sql, EarthquakeInfo.class));
        }
        return earthQuakeList;
    }
}
