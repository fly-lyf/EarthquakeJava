package earthquake.site.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by fly on 2016/10/14.
 */
@Entity
@Table(name = "earthquake_webpages", schema = "", catalog = "earthquake")
public class EarthquakeWebpages {
    private int id;
    private String eventid;
    private Integer eventseq;
    private String publishedtime;
    private String keyword;
    private String source;
    private String title;
    private String summary;
    private String content;
    private String url;
    private Timestamp crawledtime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "eventid")
    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    @Basic
    @Column(name = "eventseq")
    public Integer getEventseq() {
        return eventseq;
    }

    public void setEventseq(Integer eventseq) {
        this.eventseq = eventseq;
    }

    @Basic
    @Column(name = "publishedtime")
    public String getPublishedtime() {
        return publishedtime;
    }

    public void setPublishedtime(String publishedtime) {
        this.publishedtime = publishedtime;
    }

    @Basic
    @Column(name = "keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "crawledtime")
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

        EarthquakeWebpages webpages = (EarthquakeWebpages) o;

        if (id != webpages.id) return false;
        if (eventid != null ? !eventid.equals(webpages.eventid) : webpages.eventid != null) return false;
        if (eventseq != null ? !eventseq.equals(webpages.eventseq) : webpages.eventseq != null) return false;
        if (publishedtime != null ? !publishedtime.equals(webpages.publishedtime) : webpages.publishedtime != null)
            return false;
        if (keyword != null ? !keyword.equals(webpages.keyword) : webpages.keyword != null) return false;
        if (source != null ? !source.equals(webpages.source) : webpages.source != null) return false;
        if (title != null ? !title.equals(webpages.title) : webpages.title != null) return false;
        if (summary != null ? !summary.equals(webpages.summary) : webpages.summary != null) return false;
        if (content != null ? !content.equals(webpages.content) : webpages.content != null) return false;
        if (url != null ? !url.equals(webpages.url) : webpages.url != null) return false;
        if (crawledtime != null ? !crawledtime.equals(webpages.crawledtime) : webpages.crawledtime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eventid != null ? eventid.hashCode() : 0);
        result = 31 * result + (eventseq != null ? eventseq.hashCode() : 0);
        result = 31 * result + (publishedtime != null ? publishedtime.hashCode() : 0);
        result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (crawledtime != null ? crawledtime.hashCode() : 0);
        return result;
    }
}
