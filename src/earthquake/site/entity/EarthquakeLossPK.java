package earthquake.site.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by fly on 2017/6/12.
 */
public class EarthquakeLossPK implements Serializable {
    private int id;
    private int earthId;

    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "earth_id", nullable = false, insertable = true, updatable = true)
    @Id
    public int getEarthId() {
        return earthId;
    }

    public void setEarthId(int earthId) {
        this.earthId = earthId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeLossPK that = (EarthquakeLossPK) o;

        if (id != that.id) return false;
        if (earthId != that.earthId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + earthId;
        return result;
    }
}
