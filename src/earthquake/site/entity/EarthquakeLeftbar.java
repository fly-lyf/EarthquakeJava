package earthquake.site.entity;

import javax.persistence.*;

/**
 * Created by fly on 2017/6/14.
 */
@Entity
@Table(name = "earthquake_leftbar", schema = "", catalog = "earthquake")
public class EarthquakeLeftbar {
    private int id;
    private String column;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "column", nullable = true, insertable = true, updatable = true, length = 255)
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeLeftbar that = (EarthquakeLeftbar) o;

        if (id != that.id) return false;
        if (column != null ? !column.equals(that.column) : that.column != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (column != null ? column.hashCode() : 0);
        return result;
    }
}
