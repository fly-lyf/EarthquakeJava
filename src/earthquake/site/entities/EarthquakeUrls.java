package earthquake.site.entities;

import javax.persistence.*;

/**
 * Created by fly on 2016/10/15.
 */
@Entity
@Table(name = "earthquake_urls", schema = "earthquake", catalog = "")
public class EarthquakeUrls {
    private int id;
    private String url;
    private String notes;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "notes", nullable = true, length = 255)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeUrls urls = (EarthquakeUrls) o;

        if (id != urls.id) return false;
        if (url != null ? !url.equals(urls.url) : urls.url != null) return false;
        if (notes != null ? !notes.equals(urls.notes) : urls.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
