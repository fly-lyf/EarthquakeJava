package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/14.
 */
@Entity
@Table(name = "earthquake_nearcity_info", schema = "", catalog = "earthquake")
public class EarthquakeNearcityInfo {
    private int nearcityId;
    private String city;
    private Double distance;

    @Id
    @Column(name = "nearcity_id", nullable = false, insertable = true, updatable = true)
    public int getNearcityId() {
        return nearcityId;
    }

    public void setNearcityId(int nearcityId) {
        this.nearcityId = nearcityId;
    }

    @Basic
    @Column(name = "city", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "distance", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeNearcityInfo that = (EarthquakeNearcityInfo) o;

        if (nearcityId != that.nearcityId) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nearcityId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        return result;
    }
}
