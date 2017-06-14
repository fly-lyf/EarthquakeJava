package earthquake.site.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by fly on 2017/6/14.
 */
@Entity
@Table(name = "earthquake_info", schema = "", catalog = "earthquake")
public class EarthquakeInfo {
    private int id;
    private Integer eventId;
    private String earthquakeTitle;
    private Timestamp earthquakeTime;
    private Double longitude;
    private Double latitude;
    private Double deepth;
    private Double magnitude;
    private String location;
    private String terrain;
    private String climate;
    private String geoEnvironment;
    private String population;
    private String administrativeArea;
    private String naturalSource;

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
    @Column(name = "earthquake_title", nullable = false, insertable = true, updatable = true, length = 255)
    public String getEarthquakeTitle() {
        return earthquakeTitle;
    }

    public void setEarthquakeTitle(String earthquakeTitle) {
        this.earthquakeTitle = earthquakeTitle;
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
    @Column(name = "deepth", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getDeepth() {
        return deepth;
    }

    public void setDeepth(Double deepth) {
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
    @Column(name = "location", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "terrain", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Basic
    @Column(name = "climate", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    @Basic
    @Column(name = "geoEnvironment", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getGeoEnvironment() {
        return geoEnvironment;
    }

    public void setGeoEnvironment(String geoEnvironment) {
        this.geoEnvironment = geoEnvironment;
    }

    @Basic
    @Column(name = "population", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Basic
    @Column(name = "administrativeArea", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(String administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

    @Basic
    @Column(name = "naturalSource", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getNaturalSource() {
        return naturalSource;
    }

    public void setNaturalSource(String naturalSource) {
        this.naturalSource = naturalSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeInfo that = (EarthquakeInfo) o;

        if (id != that.id) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (earthquakeTitle != null ? !earthquakeTitle.equals(that.earthquakeTitle) : that.earthquakeTitle != null)
            return false;
        if (earthquakeTime != null ? !earthquakeTime.equals(that.earthquakeTime) : that.earthquakeTime != null)
            return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (deepth != null ? !deepth.equals(that.deepth) : that.deepth != null) return false;
        if (magnitude != null ? !magnitude.equals(that.magnitude) : that.magnitude != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (terrain != null ? !terrain.equals(that.terrain) : that.terrain != null) return false;
        if (climate != null ? !climate.equals(that.climate) : that.climate != null) return false;
        if (geoEnvironment != null ? !geoEnvironment.equals(that.geoEnvironment) : that.geoEnvironment != null)
            return false;
        if (population != null ? !population.equals(that.population) : that.population != null) return false;
        if (administrativeArea != null ? !administrativeArea.equals(that.administrativeArea) : that.administrativeArea != null)
            return false;
        if (naturalSource != null ? !naturalSource.equals(that.naturalSource) : that.naturalSource != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (earthquakeTitle != null ? earthquakeTitle.hashCode() : 0);
        result = 31 * result + (earthquakeTime != null ? earthquakeTime.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (deepth != null ? deepth.hashCode() : 0);
        result = 31 * result + (magnitude != null ? magnitude.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (terrain != null ? terrain.hashCode() : 0);
        result = 31 * result + (climate != null ? climate.hashCode() : 0);
        result = 31 * result + (geoEnvironment != null ? geoEnvironment.hashCode() : 0);
        result = 31 * result + (population != null ? population.hashCode() : 0);
        result = 31 * result + (administrativeArea != null ? administrativeArea.hashCode() : 0);
        result = 31 * result + (naturalSource != null ? naturalSource.hashCode() : 0);
        return result;
    }
}
