package earthquake.site.forms;

import earthquake.site.entities.EarthquakeUrls;

import java.util.List;

/**
 * Created by fly on 2016/10/14.
 */
public class CrawlerForm {

    public List<EarthquakeUrls> urlsList;
    public String keywords;
    public String time;

    public List<EarthquakeUrls> getUrlsList() {
        return urlsList;
    }

    public void setUrlsList(List<EarthquakeUrls> urlsList) {
        this.urlsList = urlsList;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
