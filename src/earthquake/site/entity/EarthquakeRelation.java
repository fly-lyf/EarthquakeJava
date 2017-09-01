package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/14.
 */
@Entity
@Table(name = "earthquake_relation", schema = "", catalog = "earthquake")
public class EarthquakeRelation {
    private int id;
    private Integer eventId;
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
    @Column(name = "event_id", nullable = true, insertable = true, updatable = true)
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (earthHistory != null ? !earthHistory.equals(that.earthHistory) : that.earthHistory != null) return false;
        if (earthNear != null ? !earthNear.equals(that.earthNear) : that.earthNear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (earthHistory != null ? earthHistory.hashCode() : 0);
        result = 31 * result + (earthNear != null ? earthNear.hashCode() : 0);
        return result;
    }
}
