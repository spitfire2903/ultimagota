package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("Rating")
public class Rating extends ParseObject{
    public Rating(){}

    public void setRate(int rate){
        put("rate", rate);
    }

    public int getRate(){
        return getInt("rate");
    }

    public void setTip(Tip tip){
        put("tip", tip);
    }

    public Tip getTip(){
        return (Tip) get("tip");
    }


    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }
}
