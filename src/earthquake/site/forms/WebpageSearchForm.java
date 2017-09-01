package earthquake.site.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by fly on 2016/10/14.
 */
public class WebpageSearchForm {
    public String orderName;
    public String order;
    public String eventId;
    public String typename;
    public String title;
    public String summary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date crawledStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date crawledEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date publishedStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date publishedEndTime;
    public String content;
    public String pageCount;
    public String pageNum;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCrawledStartTime() {
        return crawledStartTime;
    }

    public void setCrawledStartTime(Date crawledStartTime) {
        this.crawledStartTime = crawledStartTime;
    }

    public Date getCrawledEndTime() {
        return crawledEndTime;
    }

    public void setCrawledEndTime(Date crawledEndTime) {
        this.crawledEndTime = crawledEndTime;
    }

    public Date getPublishedStartTime() {
        return publishedStartTime;
    }

    public void setPublishedStartTime(Date publishedStartTime) {
        this.publishedStartTime = publishedStartTime;
    }

    public Date getPublishedEndTime() {
        return publishedEndTime;
    }

    public void setPublishedEndTime(Date publishedEndTime) {
        this.publishedEndTime = publishedEndTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}
