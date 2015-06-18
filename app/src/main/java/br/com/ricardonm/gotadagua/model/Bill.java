package br.com.ricardonm.gotadagua.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by ricardomiranda on 17/06/15.
 */
@ParseClassName("Bill")
public class Bill extends ParseObject {
    public void setDate(Date date){
        put("date", date);
    }

    public void setReadingDate(Date readingDate){
        put("readingDate", readingDate);
    }

    public void setMonthRef(Date monthRef){
        put("monthRef", monthRef);
    }

    public void setValueM3(Double valueM3){
        put("valueM3", valueM3);
    }

    public void setBillValue(Double billValue){
        put("billValue", billValue);
    }

    public Date getDate(){
        return getDate("date");
    }

    public Date getReadingDate(){
        return getDate("readingDate");
    }

    public Date getMonthRef(){
        return getDate("monthRef");
    }

    public Double getValueM3(){
        return getDouble("valueM3");
    }

    public Double getBillValue(){
        return getDouble("billValue");
    }

    public void setDeviceUser(DeviceUser deviceUser){
        put("deviceUser", deviceUser);
    }

    public DeviceUser getDeviceUser(){
        return (DeviceUser) get("deviceUser");
    }
}
