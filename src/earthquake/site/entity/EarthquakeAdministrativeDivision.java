package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by FLY on 2017/6/26 0026.
 */
@Entity
@Table(name = "earthquake_administrative_division", schema = "", catalog = "earthquake")
public class EarthquakeAdministrativeDivision {
    private int id;
    private String province;
    private String city;
    private String county;
    private String geoEnvironment;
    private String geoTerrain;
    private String climate;
    private String realm;
    private String population;
    private String administrativeArea;
    private String naturalSource;
    private String geoStructure;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "geo_environment", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getGeoEnvironment() {
        return geoEnvironment;
    }

    public void setGeoEnvironment(String geoEnvironment) {
        this.geoEnvironment = geoEnvironment;
    }

    @Basic
    @Column(name = "geo_terrain", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getGeoTerrain() {
        return geoTerrain;
    }

    public void setGeoTerrain(String geoTerrain) {
        this.geoTerrain = geoTerrain;
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
    @Column(name = "realm", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
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
    @Column(name = "administrative_area", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(String administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

    @Basic
    @Column(name = "natural_source", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getNaturalSource() {
        return naturalSource;
    }

    public void setNaturalSource(String naturalSource) {
        this.naturalSource = naturalSource;
    }

    @Basic
    @Column(name = "geo_structure", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getGeoStructure() {
        return geoStructure;
    }

    public void setGeoStructure(String geoStructure) {
        this.geoStructure = geoStructure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeAdministrativeDivision that = (EarthquakeAdministrativeDivision) o;

        if (id != that.id) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        if (geoEnvironment != null ? !geoEnvironment.equals(that.geoEnvironment) : that.geoEnvironment != null)
            return false;
        if (geoTerrain != null ? !geoTerrain.equals(that.geoTerrain) : that.geoTerrain != null) return false;
        if (climate != null ? !climate.equals(that.climate) : that.climate != null) return false;
        if (realm != null ? !realm.equals(that.realm) : that.realm != null) return false;
        if (population != null ? !population.equals(that.population) : that.population != null) return false;
        if (administrativeArea != null ? !administrativeArea.equals(that.administrativeArea) : that.administrativeArea != null)
            return false;
        if (naturalSource != null ? !naturalSource.equals(that.naturalSource) : that.naturalSource != null)
            return false;
        if (geoStructure != null ? !geoStructure.equals(that.geoStructure) : that.geoStructure != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (geoEnvironment != null ? geoEnvironment.hashCode() : 0);
        result = 31 * result + (geoTerrain != null ? geoTerrain.hashCode() : 0);
        result = 31 * result + (climate != null ? climate.hashCode() : 0);
        result = 31 * result + (realm != null ? realm.hashCode() : 0);
        result = 31 * result + (population != null ? population.hashCode() : 0);
        result = 31 * result + (administrativeArea != null ? administrativeArea.hashCode() : 0);
        result = 31 * result + (naturalSource != null ? naturalSource.hashCode() : 0);
        result = 31 * result + (geoStructure != null ? geoStructure.hashCode() : 0);
        return result;
    }
}
