package earthquake.site.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by fly on 2017/6/14.
 */
public class BriefSearchForm {

    public String eventId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date earthquakeTimeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date earthquakeTimeEnd;

    public String province;
    public String city;
    public String county;

    public Double magnitude;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Date getEarthquakeTimeStart() {
        return earthquakeTimeStart;
    }

    public void setEarthquakeTimeStart(Date earthquakeTimeStart) {
        this.earthquakeTimeStart = earthquakeTimeStart;
    }

    public Date getEarthquakeTimeEnd() {
        return earthquakeTimeEnd;
    }

    public void setEarthquakeTimeEnd(Date earthquakeTimeEnd) {
        this.earthquakeTimeEnd = earthquakeTimeEnd;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }
}
