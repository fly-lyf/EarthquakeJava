package earthquake.site.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by fly on 2017/6/12.
 */
@Entity
@Table(name = "earthquake_info", schema = "", catalog = "earthquake")
public class EarthquakeInfo {
    private int earthId;
    private String earthTitle;
    private Timestamp earthTime;
    private Double longitude;
    private Double latitude;
    private Integer deepth;
    private Double magnitude;
    private String city;

    @Id
    @Column(name = "earth_id", nullable = false, insertable = true, updatable = true)
    public int getEarthId() {
        return earthId;
    }

    public void setEarthId(int earthId) {
        this.earthId = earthId;
    }

    @Basic
    @Column(name = "earth_title", nullable = false, insertable = true, updatable = true, length = 255)
    public String getEarthTitle() {
        return earthTitle;
    }

    public void setEarthTitle(String earthTitle) {
        this.earthTitle = earthTitle;
    }

    @Basic
    @Column(name = "earth_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getEarthTime() {
        return earthTime;
    }

    public void setEarthTime(Timestamp earthTime) {
        this.earthTime = earthTime;
    }

    @Basic
    @Column(name = "longitude", nullable = true, insertable = true, updatable = true, precision = 3)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude", nullable = true, insertable = true, updatable = true, precision = 3)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "deepth", nullable = true, insertable = true, updatable = true)
    public Integer getDeepth() {
        return deepth;
    }

    public void setDeepth(Integer deepth) {
        this.deepth = deepth;
    }

    @Basic
    @Column(name = "magnitude", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    @Basic
    @Column(name = "city", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeInfo that = (EarthquakeInfo) o;

        if (earthId != that.earthId) return false;
        if (earthTitle != null ? !earthTitle.equals(that.earthTitle) : that.earthTitle != null) return false;
        if (earthTime != null ? !earthTime.equals(that.earthTime) : that.earthTime != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (deepth != null ? !deepth.equals(that.deepth) : that.deepth != null) return false;
        if (magnitude != null ? !magnitude.equals(that.magnitude) : that.magnitude != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = earthId;
        result = 31 * result + (earthTitle != null ? earthTitle.hashCode() : 0);
        result = 31 * result + (earthTime != null ? earthTime.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (deepth != null ? deepth.hashCode() : 0);
        result = 31 * result + (magnitude != null ? magnitude.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
