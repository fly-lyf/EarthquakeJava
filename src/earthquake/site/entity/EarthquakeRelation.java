package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/12.
 */
@Entity
@Table(name = "earthquake_relation", schema = "", catalog = "earthquake")
public class EarthquakeRelation {
    private int id;
    private Integer earthId;
    private Integer earthHistory;
    private Integer earthNear;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "earth_id", nullable = true, insertable = true, updatable = true)
    public Integer getEarthId() {
        return earthId;
    }

    public void setEarthId(Integer earthId) {
        this.earthId = earthId;
    }

    @Basic
    @Column(name = "earth_history", nullable = true, insertable = true, updatable = true)
    public Integer getEarthHistory() {
        return earthHistory;
    }

    public void setEarthHistory(Integer earthHistory) {
        this.earthHistory = earthHistory;
    }

    @Basic
    @Column(name = "earth_near", nullable = true, insertable = true, updatable = true)
    public Integer getEarthNear() {
        return earthNear;
    }

    public void setEarthNear(Integer earthNear) {
        this.earthNear = earthNear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeRelation that = (EarthquakeRelation) o;

        if (id != that.id) return false;
        if (earthId != null ? !earthId.equals(that.earthId) : that.earthId != null) return false;
        if (earthHistory != null ? !earthHistory.equals(that.earthHistory) : that.earthHistory != null) return false;
        if (earthNear != null ? !earthNear.equals(that.earthNear) : that.earthNear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (earthId != null ? earthId.hashCode() : 0);
        result = 31 * result + (earthHistory != null ? earthHistory.hashCode() : 0);
        result = 31 * result + (earthNear != null ? earthNear.hashCode() : 0);
        return result;
    }
}
