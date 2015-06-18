package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("UnitReference")
public class UnitReference extends ParseObject {
    public UnitReference(){}

    public void setName(String name){
        put("name", name);
    }

    public void setValueM3(Double valueM3){
        put("valueM3", valueM3);
    }

    public void setImgName(String imgName){
        put("imgName", imgName);
    }

    public String getName(){
        return getString("name");
    }

    public Double getValueM3(){
        return getDouble("valueM3");
    }

    public String getImgName(){
        return getString("imgName");
    }

    public static Double convertM3ToLitre(Double valueM3){
        Double result = null;

        result = valueM3 * 1000;

        return result;
    }

    public static Double convertLitreToM3(Double valueLitre){
        Double result = null;

        result = valueLitre / 1000;

        return result;
    }

    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }
}
