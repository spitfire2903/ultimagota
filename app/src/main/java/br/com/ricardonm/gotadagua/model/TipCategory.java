package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("TipCategory")
public class TipCategory extends ParseObject {
    public TipCategory(){}

    public void setName(String name){
        put("name", name);
    }

    public void setDescription(String description){
        put("description", description);
    }

    public String getName(){
        return getString("name");
    }

    public String getDescription(){
        return getString("description");
    }


    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }
}
