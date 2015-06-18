package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("Goal")
public class Goal extends ParseObject {
    public Goal(){}

    public void setGoal(int goal){
        put("goal", goal);
    }

    public int getGoal() {
        return getInt("goal");
    }

    public void setMonthRef(Date monthRef){
        put("monthRef", monthRef);
    }

    public Date getMonthRef() {
        return getDate("monthRef");
    }


    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }

}
