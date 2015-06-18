package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("Reservatory")
public class Reservatory extends ParseObject {
    public Reservatory(){}

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getText() {
        return getString("text");
    }

    public void setText(String text) {
        put("text", text);
    }

    public String getLat() {
        return getString("lat");
    }

    public void setLat(String lat) {
        put("lat", lat);
    }

    public String getLng() {
        return getString("lng");
    }

    public void setLng(String lng) {
        put("lng", lng);
    }


    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }
}
