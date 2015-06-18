package br.com.ricardonm.gotadagua.model;

import android.location.Location;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class LocationHistory extends SugarRecord<LocationHistory> {

    private Double lat;
    private Double lng;
    private long time;
    private Double accuracy;
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public LocationHistory(){
        this.createdAt = new Date();
    }

    public LocationHistory(Location location){
        Float accuracy = null;

        accuracy = new Float(location.getAccuracy());

        this.lat = location.getLatitude();
        this.lng = location.getLongitude();
        this.accuracy = Double.parseDouble(accuracy.toString());
        this.time = location.getTime();

        this.createdAt = new Date();
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

}
