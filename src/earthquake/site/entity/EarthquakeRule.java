package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/14.
 */
@Entity
@Table(name = "earthquake_rule", schema = "", catalog = "earthquake")
public class EarthquakeRule {
    private int ruleId;
    private Integer maxDeath;
    private Integer minDeath;
    private Double economyLoss;
    private Double maxMagnitude;
    private Double minMagnitude;
    private String ruleTitle;

    @Id
    @Column(name = "rule_id", nullable = false, insertable = true, updatable = true)
    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    @Basic
    @Column(name = "max_death", nullable = true, insertable = true, updatable = true)
    public Integer getMaxDeath() {
        return maxDeath;
    }

    public void setMaxDeath(Integer maxDeath) {
        this.maxDeath = maxDeath;
    }

    @Basic
    @Column(name = "min_death", nullable = true, insertable = true, updatable = true)
    public Integer getMinDeath() {
        return minDeath;
    }

    public void setMinDeath(Integer minDeath) {
        this.minDeath = minDeath;
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
    @Column(name = "max_magnitude", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getMaxMagnitude() {
        return maxMagnitude;
    }

    public void setMaxMagnitude(Double maxMagnitude) {
        this.maxMagnitude = maxMagnitude;
    }

    @Basic
    @Column(name = "min_magnitude", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getMinMagnitude() {
        return minMagnitude;
    }

    public void setMinMagnitude(Double minMagnitude) {
        this.minMagnitude = minMagnitude;
    }

    @Basic
    @Column(name = "rule_title", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRuleTitle() {
        return ruleTitle;
    }

    public void setRuleTitle(String ruleTitle) {
        this.ruleTitle = ruleTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeRule that = (EarthquakeRule) o;

        if (ruleId != that.ruleId) return false;
        if (maxDeath != null ? !maxDeath.equals(that.maxDeath) : that.maxDeath != null) return false;
        if (minDeath != null ? !minDeath.equals(that.minDeath) : that.minDeath != null) return false;
        if (economyLoss != null ? !economyLoss.equals(that.economyLoss) : that.economyLoss != null) return false;
        if (maxMagnitude != null ? !maxMagnitude.equals(that.maxMagnitude) : that.maxMagnitude != null) return false;
        if (minMagnitude != null ? !minMagnitude.equals(that.minMagnitude) : that.minMagnitude != null) return false;
        if (ruleTitle != null ? !ruleTitle.equals(that.ruleTitle) : that.ruleTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruleId;
        result = 31 * result + (maxDeath != null ? maxDeath.hashCode() : 0);
        result = 31 * result + (minDeath != null ? minDeath.hashCode() : 0);
        result = 31 * result + (economyLoss != null ? economyLoss.hashCode() : 0);
        result = 31 * result + (maxMagnitude != null ? maxMagnitude.hashCode() : 0);
        result = 31 * result + (minMagnitude != null ? minMagnitude.hashCode() : 0);
        result = 31 * result + (ruleTitle != null ? ruleTitle.hashCode() : 0);
        return result;
    }
}
