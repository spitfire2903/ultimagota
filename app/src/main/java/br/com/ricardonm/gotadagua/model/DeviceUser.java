package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("DeviceUser")
public class DeviceUser extends ParseObject {
    public DeviceUser(){}

    public DeviceUser(String deviceToken){
        this.setDeviceToken(deviceToken);
    }

    public void setDeviceToken(String deviceToken){
        put("deviceToken", deviceToken);
    }

    public String getDeviceToken(){
        return getString("deviceToken");
    }

    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }

    public void setLocations(List<LocationHistory> locations){
        put("locations", locations);
    }

    public List<LocationHistory> getLocations(){
        return getList("LocationHistory");
    }
}
