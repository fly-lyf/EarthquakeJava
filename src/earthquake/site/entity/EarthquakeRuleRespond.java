package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/14.
 */
@Entity
@Table(name = "earthquake_rule_respond", schema = "", catalog = "earthquake")
public class EarthquakeRuleRespond {
    private int id;
    private int ruleId;
    private int respondId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rule_id", nullable = false, insertable = true, updatable = true)
    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    @Basic
    @Column(name = "respond_id", nullable = false, insertable = true, updatable = true)
    public int getRespondId() {
        return respondId;
    }

    public void setRespondId(int respondId) {
        this.respondId = respondId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeRuleRespond that = (EarthquakeRuleRespond) o;

        if (id != that.id) return false;
        if (ruleId != that.ruleId) return false;
        if (respondId != that.respondId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ruleId;
        result = 31 * result + respondId;
        return result;
    }
}
