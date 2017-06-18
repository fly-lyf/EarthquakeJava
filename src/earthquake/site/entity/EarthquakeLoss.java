package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/16.
 */
@Entity
@Table(name = "earthquake_loss", schema = "", catalog = "earthquake")
public class EarthquakeLoss {
    private int id;
    private String eventId;
    private Double economyLoss;
    private Double destroyedDamage;
    private Double seriouslyDamage;
    private Double middleDamage;
    private Double lightlyDamage;
    private Integer death;
    private Integer badlyInjured;
    private Integer minorInjured;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "event_id", nullable = false, insertable = true, updatable = true, length = 16)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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
    @Column(name = "destroyed_damage", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getDestroyedDamage() {
        return destroyedDamage;
    }

    public void setDestroyedDamage(Double destroyedDamage) {
        this.destroyedDamage = destroyedDamage;
    }

    @Basic
    @Column(name = "seriously_damage", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getSeriouslyDamage() {
        return seriouslyDamage;
    }

    public void setSeriouslyDamage(Double seriouslyDamage) {
        this.seriouslyDamage = seriouslyDamage;
    }

    @Basic
    @Column(name = "middle_damage", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getMiddleDamage() {
        return middleDamage;
    }

    public void setMiddleDamage(Double middleDamage) {
        this.middleDamage = middleDamage;
    }

    @Basic
    @Column(name = "lightly_damage", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLightlyDamage() {
        return lightlyDamage;
    }

    public void setLightlyDamage(Double lightlyDamage) {
        this.lightlyDamage = lightlyDamage;
    }

    @Basic
    @Column(name = "death", nullable = true, insertable = true, updatable = true)
    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }

    @Basic
    @Column(name = "badly_injured", nullable = true, insertable = true, updatable = true)
    public Integer getBadlyInjured() {
        return badlyInjured;
    }

    public void setBadlyInjured(Integer badlyInjured) {
        this.badlyInjured = badlyInjured;
    }

    @Basic
    @Column(name = "minor_injured", nullable = true, insertable = true, updatable = true)
    public Integer getMinorInjured() {
        return minorInjured;
    }

    public void setMinorInjured(Integer minorInjured) {
        this.minorInjured = minorInjured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeLoss that = (EarthquakeLoss) o;

        if (id != that.id) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (economyLoss != null ? !economyLoss.equals(that.economyLoss) : that.economyLoss != null) return false;
        if (destroyedDamage != null ? !destroyedDamage.equals(that.destroyedDamage) : that.destroyedDamage != null)
            return false;
        if (seriouslyDamage != null ? !seriouslyDamage.equals(that.seriouslyDamage) : that.seriouslyDamage != null)
            return false;
        if (middleDamage != null ? !middleDamage.equals(that.middleDamage) : that.middleDamage != null) return false;
        if (lightlyDamage != null ? !lightlyDamage.equals(that.lightlyDamage) : that.lightlyDamage != null)
            return false;
        if (death != null ? !death.equals(that.death) : that.death != null) return false;
        if (badlyInjured != null ? !badlyInjured.equals(that.badlyInjured) : that.badlyInjured != null) return false;
        if (minorInjured != null ? !minorInjured.equals(that.minorInjured) : that.minorInjured != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (economyLoss != null ? economyLoss.hashCode() : 0);
        result = 31 * result + (destroyedDamage != null ? destroyedDamage.hashCode() : 0);
        result = 31 * result + (seriouslyDamage != null ? seriouslyDamage.hashCode() : 0);
        result = 31 * result + (middleDamage != null ? middleDamage.hashCode() : 0);
        result = 31 * result + (lightlyDamage != null ? lightlyDamage.hashCode() : 0);
        result = 31 * result + (death != null ? death.hashCode() : 0);
        result = 31 * result + (badlyInjured != null ? badlyInjured.hashCode() : 0);
        result = 31 * result + (minorInjured != null ? minorInjured.hashCode() : 0);
        return result;
    }
}
