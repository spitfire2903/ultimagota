package br.com.ricardonm.gotadagua.model;

import android.location.Location;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("LocationHistory")
public class LocationHistory extends ParseObject {
    public LocationHistory(){}

    public LocationHistory(Location location){
        Float accuracy = null;

        accuracy = new Float(location.getAccuracy());

        this.setLat(location.getLatitude());
        this.setLng(location.getLongitude());
        this.setAccuracy(Double.parseDouble(accuracy.toString()));
        this.setTime(location.getTime());
    }

    public void setLat(Double lat){
        put("lat", lat);
    }

    public void setLng(Double lng){
        put("lng", lng);
    }

    public void setTime(Long time){
        put("time", time);
    }

    public void setAccuracy(Double accuracy){
        put("accuracy", accuracy);
    }

    public Double getLat(){
        return getDouble("lat");
    }

    public Double getLng(){
        return getDouble("lng");
    }

    public Long getTime(){
        return getLong("time");
    }

    public Double getAccuracy(){
        return getDouble("accuracy");
    }

}
