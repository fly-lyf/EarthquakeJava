package earthquake.site.forms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 2016/10/14.
 */
public class CrawlerForm {

    public List<String> urls;
    public String keywords;
    public String timeStr;
    public String timeSeq;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getTimeSeq() {
        return timeSeq;
    }

    public void setTimeSeq(String timeSeq) {
        this.timeSeq = timeSeq;
    }
}
