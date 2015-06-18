package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("ReservatoryCapacity")
public class ReservatoryCapacity extends ParseObject {
    public ReservatoryCapacity(){}

    public void setValue(Double value){
        put("value", value);
    }

    public void setVolume(Double volume){
        put("volume", volume);
    }

    public void setPercChange(Double percChange){
        put("percChange", percChange);
    }

    public Double getValue(){
        return getDouble("value");
    }

    public Double getVolume(){
        return getDouble("volume");
    }

    public Double getPercChange(){
        return getDouble("percChange");
    }

    public void setReservatory(Reservatory reservatory){
        put("reservatory", reservatory);
    }

    public Reservatory getReservatory(){
        return (Reservatory) get("reservatory");
    }


    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }
}
