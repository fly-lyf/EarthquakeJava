package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/12.
 */
@Entity
@Table(name = "earthquake_loss", schema = "", catalog = "earthquake")
@IdClass(EarthquakeLossPK.class)
public class EarthquakeLoss {
    private int id;
    private int earthId;
    private Integer deathPeople;
    private Double economyLoss;
    private Double destroy;
    private Integer injury;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "earth_id", nullable = false, insertable = true, updatable = true)
    public int getEarthId() {
        return earthId;
    }

    public void setEarthId(int earthId) {
        this.earthId = earthId;
    }

    @Basic
    @Column(name = "death_people", nullable = true, insertable = true, updatable = true)
    public Integer getDeathPeople() {
        return deathPeople;
    }

    public void setDeathPeople(Integer deathPeople) {
        this.deathPeople = deathPeople;
    }

    @Basic
    @Column(name = "economy_loss", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getEconomyLoss() {
        return economyLoss;
    }

    public void setEconomyLoss(Double economyLoss) {
        this.economyLoss = economyLoss;
    }

    @Basic
    @Column(name = "destroy", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getDestroy() {
        return destroy;
    }

    public void setDestroy(Double destroy) {
        this.destroy = destroy;
    }

    @Basic
    @Column(name = "injury", nullable = true, insertable = true, updatable = true)
    public Integer getInjury() {
        return injury;
    }

    public void setInjury(Integer injury) {
        this.injury = injury;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeLoss that = (EarthquakeLoss) o;

        if (id != that.id) return false;
        if (earthId != that.earthId) return false;
        if (deathPeople != null ? !deathPeople.equals(that.deathPeople) : that.deathPeople != null) return false;
        if (economyLoss != null ? !economyLoss.equals(that.economyLoss) : that.economyLoss != null) return false;
        if (destroy != null ? !destroy.equals(that.destroy) : that.destroy != null) return false;
        if (injury != null ? !injury.equals(that.injury) : that.injury != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + earthId;
        result = 31 * result + (deathPeople != null ? deathPeople.hashCode() : 0);
        result = 31 * result + (economyLoss != null ? economyLoss.hashCode() : 0);
        result = 31 * result + (destroy != null ? destroy.hashCode() : 0);
        result = 31 * result + (injury != null ? injury.hashCode() : 0);
        return result;
    }
}
