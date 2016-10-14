package earthquake.site.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by fly on 2016/10/14.
 */
@Entity
@Table(name = "earthquake_webpages", schema = "earthquake", catalog = "")
public class EarthquakeWebpages {
    private int id;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String eventid;

    @Basic
    @Column(name = "eventid", nullable = true, length = 14)
    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    private Integer eventseq;

    @Basic
    @Column(name = "eventseq", nullable = true)
    public Integer getEventseq() {
        return eventseq;
    }

    public void setEventseq(Integer eventseq) {
        this.eventseq = eventseq;
    }

    private String publishedtime;

    @Basic
    @Column(name = "publishedtime", nullable = true, length = 30)
    public String getPublishedtime() {
        return publishedtime;
    }

    public void setPublishedtime(String publishedtime) {
        this.publishedtime = publishedtime;
    }

    private String type;

    @Basic
    @Column(name = "type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String source;

    @Basic
    @Column(name = "source", nullable = true, length = 255)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String title;

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String summary;

    @Basic
    @Column(name = "summary", nullable = true, length = -1)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private String content;

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String url;

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    private Timestamp crawledtime;

    @Basic
    @Column(name = "crawledtime", nullable = false)
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
        if (eventseq != null ? !eventseq.equals(that.eventseq) : that.eventseq != null) return false;
        if (publishedtime != null ? !publishedtime.equals(that.publishedtime) : that.publishedtime != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
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
        result = 31 * result + (eventseq != null ? eventseq.hashCode() : 0);
        result = 31 * result + (publishedtime != null ? publishedtime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (crawledtime != null ? crawledtime.hashCode() : 0);
        return result;
    }
}
