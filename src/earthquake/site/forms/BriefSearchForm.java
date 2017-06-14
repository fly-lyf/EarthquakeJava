package earthquake.site.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by fly on 2017/6/14.
 */
public class BriefSearchForm {

    public int eventId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date earthquakeTimeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date earthquakeTimeEnd;
    public String location;
    public double magnitude;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }
}
