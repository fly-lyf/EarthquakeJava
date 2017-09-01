package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/1.
 */
@Entity
@Table(name = "earthquake_deploy", schema = "earthquake", catalog = "")
public class EarthquakeDeployEntity {
    private int deployId;
    private String province;
    private Integer respondId;
    private String start;
    private String deploy;
    private String end;

    @Id
    @Column(name = "deploy_id")
    public int getDeployId() {
        return deployId;
    }

    public void setDeployId(int deployId) {
        this.deployId = deployId;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "respond_id")
    public Integer getRespondId() {
        return respondId;
    }

    public void setRespondId(Integer respondId) {
        this.respondId = respondId;
    }

    @Basic
    @Column(name = "start")
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Basic
    @Column(name = "deploy")
    public String getDeploy() {
        return deploy;
    }

    public void setDeploy(String deploy) {
        this.deploy = deploy;
    }

    @Basic
    @Column(name = "end")
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeDeployEntity that = (EarthquakeDeployEntity) o;

        if (deployId != that.deployId) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (respondId != null ? !respondId.equals(that.respondId) : that.respondId != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (deploy != null ? !deploy.equals(that.deploy) : that.deploy != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deployId;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (respondId != null ? respondId.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (deploy != null ? deploy.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
