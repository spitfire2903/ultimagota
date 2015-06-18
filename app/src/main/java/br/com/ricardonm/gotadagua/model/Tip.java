package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("Tip")
public class Tip extends ParseObject {
    public Tip(){}

    public void setTitle(String title){
        put("title", title);
    }

    public void setDescription(String description){
        put("description", description);
    }

    public void setText(String text){
        put("text", text);
    }

    public void setCategories(List<TipCategory> categories){
        put("categories", categories);
    }

    public String getTitle(){
        return getString("title");
    }

    public String getDescription(){
        return getString("description");
    }

    public String getText(){
        return getString("text");
    }

    public List<TipCategory> getCategories(){
        return getList("categories");
    }


    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }

}
