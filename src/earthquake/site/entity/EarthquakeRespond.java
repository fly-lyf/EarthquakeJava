package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/12.
 */
@Entity
@Table(name = "earthquake_respond", schema = "", catalog = "earthquake")
public class EarthquakeRespond {
    private int respondId;
    private String respondTitle;
    private String respondLead;
    private String respondOrganize;

    @Id
    @Column(name = "respond_id", nullable = false, insertable = true, updatable = true)
    public int getRespondId() {
        return respondId;
    }

    public void setRespondId(int respondId) {
        this.respondId = respondId;
    }

    @Basic
    @Column(name = "respond_title", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRespondTitle() {
        return respondTitle;
    }

    public void setRespondTitle(String respondTitle) {
        this.respondTitle = respondTitle;
    }

    @Basic
    @Column(name = "respond_lead", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRespondLead() {
        return respondLead;
    }

    public void setRespondLead(String respondLead) {
        this.respondLead = respondLead;
    }

    @Basic
    @Column(name = "respond_organize", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRespondOrganize() {
        return respondOrganize;
    }

    public void setRespondOrganize(String respondOrganize) {
        this.respondOrganize = respondOrganize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeRespond that = (EarthquakeRespond) o;

        if (respondId != that.respondId) return false;
        if (respondTitle != null ? !respondTitle.equals(that.respondTitle) : that.respondTitle != null) return false;
        if (respondLead != null ? !respondLead.equals(that.respondLead) : that.respondLead != null) return false;
        if (respondOrganize != null ? !respondOrganize.equals(that.respondOrganize) : that.respondOrganize != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = respondId;
        result = 31 * result + (respondTitle != null ? respondTitle.hashCode() : 0);
        result = 31 * result + (respondLead != null ? respondLead.hashCode() : 0);
        result = 31 * result + (respondOrganize != null ? respondOrganize.hashCode() : 0);
        return result;
    }
}
