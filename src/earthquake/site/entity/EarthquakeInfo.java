package earthquake.site.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by FLY on 2017/6/28 0028.
 */
@Entity
@Table(name = "earthquake_info", schema = "", catalog = "earthquake")
public class EarthquakeInfo {
    private int id;
    private String eventId;
    private Double longitude;
    private Double latitude;
    private Double magnitude;
    private String intensity;
    private Double depth;
    private Timestamp earthquakeTime;
    private String province;
    private String city;
    private String county;
    private String undealed;
    private String cause;
    private String suggestion;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "event_id", nullable = true, insertable = true, updatable = true, length = 20)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "longitude", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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
    @Column(name = "intensity", nullable = true, insertable = true, updatable = true, length = 255)
    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    @Basic
    @Column(name = "depth", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    @Basic
    @Column(name = "earthquake_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getEarthquakeTime() {
        return earthquakeTime;
    }

    public void setEarthquakeTime(Timestamp earthquakeTime) {
        this.earthquakeTime = earthquakeTime;
    }

    @Basic
    @Column(name = "province", nullable = true, insertable = true, updatable = true, length = 255)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
    @Column(name = "county", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Basic
    @Column(name = "undealed", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUndealed() {
        return undealed;
    }

    public void setUndealed(String undealed) {
        this.undealed = undealed;
    }

    @Basic
    @Column(name = "cause", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Basic
    @Column(name = "suggestion", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeInfo that = (EarthquakeInfo) o;

        if (id != that.id) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (magnitude != null ? !magnitude.equals(that.magnitude) : that.magnitude != null) return false;
        if (intensity != null ? !intensity.equals(that.intensity) : that.intensity != null) return false;
        if (depth != null ? !depth.equals(that.depth) : that.depth != null) return false;
        if (earthquakeTime != null ? !earthquakeTime.equals(that.earthquakeTime) : that.earthquakeTime != null)
            return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        if (undealed != null ? !undealed.equals(that.undealed) : that.undealed != null) return false;
        if (cause != null ? !cause.equals(that.cause) : that.cause != null) return false;
        if (suggestion != null ? !suggestion.equals(that.suggestion) : that.suggestion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (magnitude != null ? magnitude.hashCode() : 0);
        result = 31 * result + (intensity != null ? intensity.hashCode() : 0);
        result = 31 * result + (depth != null ? depth.hashCode() : 0);
        result = 31 * result + (earthquakeTime != null ? earthquakeTime.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (undealed != null ? undealed.hashCode() : 0);
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        result = 31 * result + (suggestion != null ? suggestion.hashCode() : 0);
        return result;
    }
}
