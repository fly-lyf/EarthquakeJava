package earthquake.site.dao;

import earthquake.site.entity.EarthquakeLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by frankstar on 2017/6/9.
 */
@Repository("earthQuakeLossDAO")
public class EarthquakeLossDAOImpl implements EarthquakeLossDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public EarthquakeLoss getEarthQuakeLossByEarthID(int earth_id) {

        final EarthquakeLoss earthQuakeLoss = new EarthquakeLoss();
        String sql = "select * from earth_loss where earth_id = " + earth_id;

        jdbcTemplate.query(sql, resultSet -> {
            earthQuakeLoss.setDeathPeople(resultSet.getInt("death_people"));
            earthQuakeLoss.setDestroy(resultSet.getDouble("destroy"));
            earthQuakeLoss.setEarthId(resultSet.getInt("earth_id"));
            earthQuakeLoss.setEconomyLoss(resultSet.getDouble("economy_loss"));
            earthQuakeLoss.setId(resultSet.getInt("id"));
            earthQuakeLoss.setInjury(resultSet.getInt("injury"));
        });
        return earthQuakeLoss;
    }

    @Override
    public void addEarthQuakeLoss(int earth_id, int death_people, int injury, double economy_loss, double destroy) {

        String sql = "insert into earth_loss (earth_id, death_people, economy_loss, destroy, injury) values (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[]{earth_id, death_people, economy_loss, destroy, injury});

    }
}
