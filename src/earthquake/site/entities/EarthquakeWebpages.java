package earthquake.site.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by fly on 2016/10/9.
 */
@Entity
@Table(name = "earthquake_webpages", schema = "", catalog = "earthquake")
public class EarthquakeWebpages {
    private int id;
    private String urlhash;
    private String url;
    private String title;
    private String h1;
    private String h2;
    private String topic;
    private String content;
    private String htmlpath;
    private Timestamp crawledtime;
    private String allwords;
    private String denywords;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "urlhash")
    public String getUrlhash() {
        return urlhash;
    }

    public void setUrlhash(String urlhash) {
        this.urlhash = urlhash;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "h1")
    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    @Basic
    @Column(name = "h2")
    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    @Basic
    @Column(name = "topic")
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
    @Column(name = "htmlpath")
    public String getHtmlpath() {
        return htmlpath;
    }

    public void setHtmlpath(String htmlpath) {
        this.htmlpath = htmlpath;
    }

    @Basic
    @Column(name = "crawledtime")
    public Timestamp getCrawledtime() {
        return crawledtime;
    }

    public void setCrawledtime(Timestamp crawledtime) {
        this.crawledtime = crawledtime;
    }

    @Basic
    @Column(name = "allwords")
    public String getAllwords() {
        return allwords;
    }

    public void setAllwords(String allwords) {
        this.allwords = allwords;
    }

    @Basic
    @Column(name = "denywords")
    public String getDenywords() {
        return denywords;
    }

    public void setDenywords(String denywords) {
        this.denywords = denywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EarthquakeWebpages that = (EarthquakeWebpages) o;

        if (id != that.id) return false;
        if (urlhash != null ? !urlhash.equals(that.urlhash) : that.urlhash != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (h1 != null ? !h1.equals(that.h1) : that.h1 != null) return false;
        if (h2 != null ? !h2.equals(that.h2) : that.h2 != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (htmlpath != null ? !htmlpath.equals(that.htmlpath) : that.htmlpath != null) return false;
        if (crawledtime != null ? !crawledtime.equals(that.crawledtime) : that.crawledtime != null) return false;
        if (allwords != null ? !allwords.equals(that.allwords) : that.allwords != null) return false;
        if (denywords != null ? !denywords.equals(that.denywords) : that.denywords != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (urlhash != null ? urlhash.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (h1 != null ? h1.hashCode() : 0);
        result = 31 * result + (h2 != null ? h2.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (htmlpath != null ? htmlpath.hashCode() : 0);
        result = 31 * result + (crawledtime != null ? crawledtime.hashCode() : 0);
        result = 31 * result + (allwords != null ? allwords.hashCode() : 0);
        result = 31 * result + (denywords != null ? denywords.hashCode() : 0);
        return result;
    }
}
