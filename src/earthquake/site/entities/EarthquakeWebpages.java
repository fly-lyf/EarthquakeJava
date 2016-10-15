package earthquake.site.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by fly on 2016/10/15.
 */
@Entity
@Table(name = "earthquake_webpages", schema = "earthquake", catalog = "")
public class EarthquakeWebpages {
    private int id;
    private String eventid;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String publishedtime;
    private String typename;
    private String source;
    private String title;
    private String summary;
    private String content;
    private String url;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp crawledtime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "eventid", nullable = true, length = 14)
    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    @Basic
    @Column(name = "publishedtime", nullable = true, length = 30)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public String getPublishedtime() {
        return publishedtime;
    }

    public void setPublishedtime(String publishedtime) {
        this.publishedtime = publishedtime;
    }

    @Basic
    @Column(name = "typename", nullable = true, length = 255)
    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Basic
    @Column(name = "source", nullable = true, length = 255)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "summary", nullable = true, length = -1)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "crawledtime", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getCrawledtime() {
        return crawledtime;
    }

    public void setCrawledtime(Timestamp crawledtime) {
        this.crawledtime = crawledtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeWebpages that = (EarthquakeWebpages) o;

        if (id != that.id) return false;
        if (eventid != null ? !eventid.equals(that.eventid) : that.eventid != null) return false;
        if (publishedtime != null ? !publishedtime.equals(that.publishedtime) : that.publishedtime != null)
            return false;
        if (typename != null ? !typename.equals(that.typename) : that.typename != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (crawledtime != null ? !crawledtime.equals(that.crawledtime) : that.crawledtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eventid != null ? eventid.hashCode() : 0);
        result = 31 * result + (publishedtime != null ? publishedtime.hashCode() : 0);
        result = 31 * result + (typename != null ? typename.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (crawledtime != null ? crawledtime.hashCode() : 0);
        return result;
    }
}
