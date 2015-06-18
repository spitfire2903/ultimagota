package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("Reading")
public class Reading extends ParseObject{
    public Reading(){}

    public void setValue(Double value){
        put("value", value);
    }

    public Double getValue() {
        return getDouble("value");
    }

    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }
}
